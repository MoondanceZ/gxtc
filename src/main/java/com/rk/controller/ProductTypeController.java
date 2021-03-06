package com.rk.controller;

import com.rk.common.exception.DataNotFoundException;
import com.rk.dto.response.LayPage;
import com.rk.dto.response.ReturnResult;
import com.rk.dto.request.PageRequest;
import com.rk.entity.ProductType;
import com.rk.service.interfaces.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
@Controller
@RequestMapping("/productType")
public class ProductTypeController extends BaseController  {

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    @ResponseBody
    public LayPage<List<ProductType>> PageList(PageRequest pageRequest) {
        return productTypeService.getPageList(pageRequest);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProductType(@RequestParam(value = "id", required = false) Integer id, Model model) throws DataNotFoundException {
        if (id != null) {
            ProductType productType = productTypeService.getByPrimaryKey(id);
            if (productType == null)
                throw new DataNotFoundException("产品类型不存在");
            model.addAttribute("productType", productType);
        } else {
            model.addAttribute("productType", new ProductType());
        }
        return "admin/productType/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody()
    public ReturnResult Save(@RequestBody @Valid ProductType productType) {
/*        //校验
        ReturnResult errorReturnResult = ValidatorHelper.getErrorReturnResult(errors);
        if (errorReturnResult != null) return errorReturnResult;*/

        return productTypeService.updateOrAdd(productType);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody()
    public ReturnResult Delete(@RequestParam("ids[]") Integer[] ids) {
        return productTypeService.delete(ids);
    }
}
