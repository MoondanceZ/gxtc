package com.rk.controller;

import com.rk.common.exception.DataNotFoundException;
import com.rk.dto.response.LayPage;
import com.rk.dto.response.ReturnResult;
import com.rk.dto.request.ProductPageRequest;
import com.rk.entity.Product;
import com.rk.service.interfaces.ProductService;
import com.rk.service.interfaces.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * Created by Qin_Yikai on 2018-09-29.
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    @ResponseBody
    public LayPage<List<Product>> PageList(ProductPageRequest pageRequest) {
        return productService.getPageList(pageRequest);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProduct(@RequestParam(value = "id", required = false) Long id, Model model) throws DataNotFoundException {
        if (id != null) {
            Product product = productService.getByPrimaryKey(id);
            if (product == null)
                throw new DataNotFoundException("产品不存在");
            model.addAttribute("product", product);
        } else {
            model.addAttribute("product", new Product());
        }

        model.addAttribute("enableTypes", productTypeService.getEnableTypes());
        //model.addAttribute("productStatus", ProductStatus.ToList());
        return "admin/product/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody()
    public ReturnResult Save(HttpServletRequest request, @RequestBody @Valid  Product product)
            throws IOException {

        if (product.getId() != null)
            product.setModifyDate(new Date());
        else
            product.setCreateDate(new Date());

        if(product.getUnifiedPrice() == null)
            product.setUnifiedPrice(false);

        return productService.updateOrAdd(product);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody()
    public ReturnResult Delete(@RequestParam("ids[]") Long[] ids) {
        return productService.delete(ids);
    }
}
