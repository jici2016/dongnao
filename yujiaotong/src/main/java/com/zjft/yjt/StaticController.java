package com.zjft.yjt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @Package: com.zjft.yjt
 * @author liuming
 * @date 2018年1月25日
 *
 */
@Controller
public class StaticController {
	@GetMapping("/hello")
	public String a360() {
		return "/hello";
	}
}
