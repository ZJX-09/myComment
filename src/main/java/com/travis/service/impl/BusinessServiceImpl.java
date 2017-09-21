package com.travis.service.impl;

import com.travis.bean.Business;
import com.travis.bean.Page;
import com.travis.constant.CategoryConst;
import com.travis.dao.BusinessDao;
import com.travis.dto.BusinessDto;
import com.travis.dto.BusinessListDto;
import com.travis.service.BusinessService;
import com.travis.util.CommonUtil;
import com.travis.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {


    @Resource
    private BusinessDao businessDao;

    @Value("${businessImage.savePath}")
    private String savePath;

    @Value("${businessImage.url}")
    private String url;

    @Override
    public boolean add(BusinessDto businessDto) {

        Business business = new Business();
        BeanUtils.copyProperties(businessDto, business);

        if (businessDto.getImgFile() != null && businessDto.getImgFile().getSize() > 0) {
            try {
                String fileName = FileUtil.save(businessDto.getImgFile(), savePath);
                business.setImgFileName(fileName);
                // 默认人均为0
                business.setPrice(0D);
                // 默认已售数量为0
                business.setNumber(0);
                // 默认评论总次数为0
                business.setCommentTotalNum(0L);
                // 默认评论星星总数为0
                business.setStarTotalNum(0L);
                businessDao.insert(business);
                return true;
            } catch (IllegalStateException | IOException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public BusinessDto getById(Long id) {

        BusinessDto result = new BusinessDto();
        Business business = businessDao.selectById(id);
        BeanUtils.copyProperties(business, result);
        result.setImg(url + business.getImgFileName());

        result.setStar(this.getStar(business));
        result.setPrice((double)this.getPrice(business));
        return result;

    }

    @Override
    public List<BusinessDto> searchByPage(BusinessDto businessDto) {

        List<BusinessDto> result = new ArrayList<>();
        Business businessForSelect = new Business();
        BeanUtils.copyProperties(businessDto, businessForSelect);
        List<Business> list = businessDao.selectByPage(businessForSelect);
        for (Business business : list) {
            BusinessDto businessDtoTemp = new BusinessDto();
            result.add(businessDtoTemp);
            BeanUtils.copyProperties(business, businessDtoTemp);
            businessDtoTemp.setImg(url + business.getImgFileName());
            businessDtoTemp.setStar(this.getStar(business));
            businessDtoTemp.setPrice((double)this.getPrice(business));
        }
        return result;
    }

    @Override
    public BusinessListDto searchByPageForApi(BusinessDto businessDto) {

        BusinessListDto result = new BusinessListDto();
        //组织查询条件
        Business businessForSelect = new Business();
        BeanUtils.copyProperties(businessDto, businessForSelect);
        // 当关键字不为空时，把关键字的值分别设置到标题、副标题、描述中
        // TODO 改进做法：全文检索
        if (!CommonUtil.isEmpty(businessDto.getKeyword())) {
            businessForSelect.setTitle(businessDto.getKeyword());
            businessForSelect.setSubtitle(businessDto.getKeyword());
            businessForSelect.setDesc(businessDto.getKeyword());
        }
        // 当类别为全部(all)时，需要将类别清空，不作为过滤条件
        if (businessDto.getCategory() != null && CategoryConst.ALL.equals(businessDto.getCategory())) {
            businessForSelect.setCategory(null);
        }
        // 前端app页码从0开始计算，这里需要+1
        int currentPage = businessForSelect.getPage().getCurrentPage();
        businessForSelect.getPage().setCurrentPage(currentPage + 1);
        //从DAO层查出相关数据
        List<Business> list = businessDao.selectLikeByPage(businessForSelect);
        //  组织成要返回的数据格式
        // 经过查询后根据page对象设置hasMore
        Page page = businessForSelect.getPage();
        result.setHasMore(page.getCurrentPage() < page.getTotalPage());
        // 对查询出的结果进行格式化
        for (Business business : list) {
            BusinessDto businessDtoTemp = new BusinessDto();
            result.getData().add(businessDtoTemp);
            BeanUtils.copyProperties(business, businessDtoTemp);
            businessDtoTemp.setImg(url + business.getImgFileName());
            // 为兼容前端mumber这个属性
            businessDtoTemp.setMumber(business.getNumber());
            businessDtoTemp.setStar(this.getStar(business));
            businessDtoTemp.setPrice((double)this.getPrice(business));
            // 为兼容前端subTitle这个属性
            businessDtoTemp.setSubTitle(business.getSubtitle());
        }
        return result;
    }

    @Override
    public boolean remove(Long id) {

        Business business = businessDao.selectById(id);
        int deleteRow  = businessDao.delete(id);
        FileUtil.delete(savePath + business.getImgFileName());
        return deleteRow == 1;

    }

    @Override
    public boolean modify(BusinessDto dto) {

        Business business = new Business();
        BeanUtils.copyProperties(dto,business);
        // 判断是否有新上传文件
        MultipartFile imgFile =  dto.getImgFile();
        String fileName = null;
        if(imgFile != null && imgFile.getSize() > 0)
        {
            try {
                fileName = FileUtil.save(imgFile, savePath);
                business.setImgFileName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        // 更新数据库内容
        if(businessDao.update(business) != 1) return false;

        if (fileName != null)
            FileUtil.delete(savePath + dto.getImgFileName());

        return true;
    }


    private int getStar(Business business) {
        if(business.getStarTotalNum() != null && business.getCommentTotalNum() != null && business.getCommentTotalNum() != 0) {
            return (int)(business.getStarTotalNum() / business.getCommentTotalNum());
        } else {
            return 0;
        }
    }

    private int getPrice(Business business) {
        if(business.getPrice() != null && business.getNumber() != null && business.getNumber() != 0) {
            return (int)(business.getPrice() / business.getNumber());
        } else {
            return 0;
        }
    }
}
