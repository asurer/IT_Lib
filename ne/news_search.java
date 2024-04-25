package com.situ.springboot.controller;

import com.situ.springboot.pojo.entity.Blog;
import com.situ.springboot.service.IBlogService;
import com.situ.springboot.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model) {
        Blog blog = blogService.selectById(id);
        model.addAttribute("blog", blog);
        return "/blog/update";
    }

    @RequestMapping("/update")
    public String update(Blog blog) {
        blogService.update(blog);
        return "redirect:/blog/selectByPage";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/blog/add";
    }

    @RequestMapping("/add")
    public String add(Blog blog) {
        blogService.add(blog);
        return "redirect:/blog/selectByPage";
    }


    // /blog/deleteById?id=23
    @RequestMapping("/deleteById")
    public String deleteById(Integer id) {
        blogService.deleteById(id);
        //redirect:重定向，告诉浏览器发送请求 /blog/selectAll
        //删除完之后，应该查找展示最新的数据
        return "redirect:/blog/selectByPage";
        //return "/blog/selectAll";
    }

    @RequestMapping("/deleteAll")
    public String deleteAll(Integer[] ids) {
        blogService.deleteAll(ids);
        return "redirect:/blog/selectByPage";
    }

    @RequestMapping("/selectByPage")
    public String selectByPage(@RequestParam(defaultValue = "1") Integer pageNo,
                               @RequestParam(defaultValue = "10") Integer pageSize, Model model) {
        System.out.println("BlogController.selectByPage");
        PageInfo pageInfo = blogService.selectByPage(pageNo, pageSize);
        System.out.println("pageInfo:" + pageInfo);
        //把pageInfo数据放到内存里面
        model.addAttribute("pageInfo", pageInfo);
        //转发到blog_list界面展示
        return "/blog/list";
    }

    @RequestMapping("/selectAll")
    public String selectAll(Model model) {
        // soutm
        System.out.println("BlogController.selectAll");
        List<Blog> list = blogService.selectAll();

        //把list数据放到内存里面
        model.addAttribute("list", list);
        //转发到blog_list界面展示
        return "/blog/list";
    }

    // @ResponseBody 返回json格式数据
    // json集合表示: [{'id':1,'name':'zhansgan'},{}]
    // json对象: {'id':1,'name':'zhansgan'}
    // http://localhost:8080/blog/selectAll
    @RequestMapping("/selectAll1")
    @ResponseBody
    public List<Blog> selectAll1() {
        // soutm
        System.out.println("BlogController.selectAll");
        List<Blog> list = blogService.selectAll();

        return list;
    }
}
