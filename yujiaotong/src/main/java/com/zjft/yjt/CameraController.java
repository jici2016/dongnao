package com.zjft.yjt;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 *
 * @Package: com.zjft.yjt
 * @author liuming
 * @date 2018年1月25日
 *
 */
@RestController
@RequestMapping("/Pstest/lss/room")
public class CameraController {
	
	/**
	 * 开发者id
	 */
	@Value("${yjt.app_id}")
	private String app_id;
	
	/**
	 * sdk密钥
	 */
	@Value("${yjt.app_sdk_key}")
	private String app_sdk_key;
	
	/**
	 * 服务器密钥
	 */
	@Value("${yjt.app_server_key}")
	private String app_server_key;
	
	/**
	 * 摄像头编号
	 */
	@Value("${yjt.sn}")
	private String sn;
	
	/**
	 * 360根地址
	 */
	String baseUrl = "https://api.dev.jia.360.cn";
	
	//老接口地址
	@Deprecated
	String baseOldUrl = "https://open.jia.360.cn";
	/**
	 * 登陆接口地址
	 */
	String loginUri = "/app/login";
	/**
	 * 获取sn接口地址
	 */
	String getSnUri = "/camera/info";
	
	/**
	 * 摄像机接口地址
	 */
	String cameraUri = "/camera/update";
	
	/**
	 * 信令接口地址
	 */
	String commandUri = "/app/commandServer";
	
	
	
	
	/**
	 * 打开摄像头
	 * @param uid
	 * @param response 
	 * @return
	 */
	@RequestMapping("/openCameraPlay")
	public Object openCamera(@RequestParam(name="uid",defaultValue="1")String uid, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("app_id", app_id);
		map.put("uid", uid);
		map.put("sn", sn);
		
		String sn_token = getSntoken(uid,sn);
		map.put("sn_token", sn_token);
		
		System.out.println("sn_token:"+sn_token);
		String jret = login(uid);
		ObjectMapper mapper = new ObjectMapper();
		LoginResp loginResp  = null;
		String usid =  null;
		try {
			loginResp =	mapper.readValue(jret, LoginResp.class);
			String errorCode = loginResp.getErrorCode();
			if(errorCode.equals("0")) {
				usid = loginResp.getUsid();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(usid!=null) {
			map.put("usid", usid);
			userCache.put(uid, usid);
		 jret =	getSn(app_id, uid, usid, sn);
		 
//			ObjectMapper mapper = new ObjectMapper();
			SnInfoResp snInfoResp = null;
			try {
				snInfoResp = mapper.readValue(jret, SnInfoResp.class);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if(snInfoResp!=null) {
				System.out.println("snInfoResp:"+snInfoResp);
			}
			LinkedHashMap<String, String> lmap = new LinkedHashMap<>();
			lmap.put("app_id", app_id);
			lmap.put("sn",sn);
			lmap.put("sn_token",sn_token);
			lmap.put("uid",uid);
			lmap.put("usid",usid);
			String signkey = calcSign(lmap, app_server_key);
			map.put("signKey", signkey);
		}
		System.out.println("响应结果："+map);
		return map;
	}
	
	HashMap<String,String>userCache = new HashMap<String ,String>();
	
	/**
	 * 登陆接口
	 * @param uid
	 * @return
	 */
	public String login(String uid ) {
		StringBuffer sb = new StringBuffer();
		String uri = baseUrl+loginUri;
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("app_id", app_id);
		map.put("uid",uid);
		String sign = calcSign(map, app_server_key);
		sb.append("app_id").append("=").append(app_id).append("&")
		.append("uid").append("=").append(uid).append("&")
		.append("sig").append("=").append(sign).append("");
		String param = sb.toString();
		sb.delete(0, sb.length());
		String ret = GWTools.sendPost(uri, param );
		return ret;
	}
	
	/**
	 * 获取sn信息接口
	 * @param appId
	 * @param uid
	 * @param usid
	 * @param sns
	 * @return
	 */
	public String getSn(String appId,String uid,String usid,String sns) {
		StringBuffer sb = new StringBuffer();
		String uri = baseUrl+getSnUri;
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		
		map.put("app_id", app_id);
		map.put("sn",sns);
		map.put("uid",uid);
		map.put("usid",usid);
		
		String sign = calcSign(map, app_server_key);
		 try {
				usid = URLEncoder.encode(usid, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		sb.append("app_id").append("=").append(app_id).append("&")
		.append("uid").append("=").append(uid).append("&")
		.append("usid").append("=").append(usid).append("&")
		.append("sn").append("=").append(sns).append("&")
		.append("sig").append("=").append(sign).append("");
		String param = sb.toString();
		sb.delete(0, sb.length());
		String ret = GWTools.sendPost(uri, param );
		return ret;
	}
	
	
	/**
	 * 本机修改摄像机接口
	 * @param uid
	 * @param command
	 * @return
	 */
	@RequestMapping("/updateCamera")
	public Object updateCamera(String uid, String desc, String title) {
		String usid = userCache.get(uid);
		if(usid==null) {
			return uid+"未登录！";
		}
		return updateCamera(uid, sn, desc, title);
	}
	
	
	/**
	 * 修改摄像机接口
	 * @param uid
	 * @param sn
	 * @param desc 
	 * @param title 
	 * @return
	 */
	public String updateCamera(String uid,String sn, String desc, String title) {
		String usid = userCache.get(uid);
		if(usid==null) {
			return uid+"未登录！";
		}
		StringBuffer sb = new StringBuffer();
		String uri = baseUrl+cameraUri;
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		//[app_id, desc, sn, sn_token, title, uid, usid]
		map.put("app_id", app_id);
		map.put("desc", desc);
		String sn_token = getSntoken(uid, sn);
		map.put("sn",sn);
		map.put("sn_token",sn_token);
		map.put("title",title);
		map.put("uid",uid);
		map.put("usid",usid);
		
		String sign = calcSign(map, app_server_key);
		 try {
				usid = URLEncoder.encode(usid, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 try {
			 sn_token = URLEncoder.encode(sn_token, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		sb.append("app_id").append("=").append(app_id).append("&")
		.append("uid").append("=").append(uid).append("&")
		.append("usid").append("=").append(usid).append("&")
		.append("sn").append("=").append(sn).append("&")
		.append("sn_token").append("=").append(sn_token).append("&")
		.append("title").append("=").append(title).append("&")
		.append("desc").append("=").append(desc).append("&")
		.append("sig").append("=").append(sign).append("");
		String param = sb.toString();
		sb.delete(0, sb.length());
		String ret = GWTools.sendPost(uri, param );
		return ret;
	}
	
	/**
	 * 本机信令服务
	 * @param uid
	 * @param command
	 * @return
	 */
	@RequestMapping("/command")
	public Object command(String uid,String command) {
		String usid = userCache.get(uid);
		if(usid==null) {
			return uid+"未登录！";
		}
		return commandServer(app_id, uid, usid, sn, getSntoken(uid, sn), command);
	}
	
	
	//360信令接口
	public Object commandServer(String app_id,String uid,String usid,String sn,String sn_token,String command) {
		StringBuffer sb = new StringBuffer();
		String uri = baseUrl+commandUri;
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		//[app_id, command, sn, sn_token, uid, usid]
		map.put("app_id", app_id);
		map.put("command",command);
		map.put("sn",sn);
		map.put("sn_token",sn_token);
		map.put("uid",uid);
		map.put("usid",usid);
		
		String sign = calcSign(map, app_server_key);
		 try {
				usid = URLEncoder.encode(usid, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		sb.append("app_id").append("=").append(app_id).append("&")
		.append("command").append("=").append(command).append("&")
		.append("sn").append("=").append(sn).append("&")
		.append("sn_token").append("=").append(sn_token).append("&")
		.append("uid").append("=").append(uid).append("&")
		.append("usid").append("=").append(usid).append("&")
		.append("sig").append("=").append(sign);
		String param = sb.toString();
		sb.delete(0, sb.length());
		String ret = GWTools.sendPost(uri, param );
		ObjectMapper mapper = new ObjectMapper();
		CommandResp commandResp = null;
		try {
			commandResp = mapper.readValue(ret, CommandResp.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(commandResp!=null) {
			System.out.println("commandResp:"+commandResp);
		}
		return null;
	}
	
	
	/**
	 * 生成sig
	 * @param map
	 * @param signKey
	 * @return
	 */
	public static String calcSign(LinkedHashMap<String, String> map,String signKey) {
		StringBuffer sb = new StringBuffer();
		String sign = null;
		Set<Entry<String,String>> entrySet = map.entrySet();
		for (Entry<String, String> entry : entrySet) {
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
//		System.out.println(sb.toString());
		//去掉&
		
		String params = sb.deleteCharAt(sb.length()-1).toString();
//		System.out.println("params:"+params);
		String content = params+signKey;
//		System.out.println("content:"+content);
		sign = GWTools.MD5(content);
//		System.out.println("sign:"+sign);
		return sign;
	}
	
	/**
	 * 生产sn_token
	 * @param uid
	 * @param sn
	 * @return
	 */
	public String getSntoken(String uid,String sn) {
		String testSnToken= null;
		try {
			String iv = app_server_key.substring(0, 16);
        	long epoch = System.currentTimeMillis()/1000+86400;
        	
        	String SnTokensig=epoch+","+app_id+","+uid+","+sn;
        	System.out.println("content:"+SnTokensig);
        	testSnToken= AesCBC.getInstance().encrypt(SnTokensig, "utf-8",app_server_key, iv);
//        	System.out.println("Qihoo360Camera:tOkenString:" +testSnToken );
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}

		
		
		return testSnToken;
		
	}
	
	
	
	public static  void main(String[] args) {
//		LinkedHashMap<String, String> map = new LinkedHashMap<>();
//		String demoappid = "BCSQOMKSQOMKSQOM";
//		demoappid = "DCAADEKQMSOKQMSO";
//		String demouid = "1000";
//		map.put("app_id", demoappid);
//		map.put("uid",demouid);
//		String signKey="598c6bca44dc001f2b14d124b24f2da7";
//		calcSign(map, signKey);
		ArrayList<String>list = new ArrayList<>();
		list.add("uid");
		list.add("usid");
		list.add("app_id");
		list.add("sn");
		list.add("sn_token");
//		list.add("title");
//		list.add("desc");
//		list.add("c");
		Collections.sort(list);
		System.out.println(list);
		LinkedHashMap<String, String> lmap = new LinkedHashMap<>();
		lmap.put("app_id", "12");
		lmap.put("sn","360H1834302");
		lmap.put("sn_token","lM11Y1biuL1XbE5CszAdtZ6DJRD+eCCULir3qA++qbeoaWkyPRIHaR9oemiVaCHN");
		lmap.put("uid","100");
		lmap.put("usid","b:12:100:CLxh1KCJkZCIFSu6atKgrZA78JKkCmxgW/5Ux5eIZyM=");
		String signkey = calcSign(lmap, "9a256fd6bae55aaba4ea7446869c3968");
		//[app_id, sn, sn_token, uid, usid]
//		lmap.put("app_id", "DCAADEKQMSOKQMSO");
//		lmap.put("sn","360H1834302");
//		lmap.put("sn_token","lM11Y1biuL1XbE5CszAdtZ6DJRD+eCCULir3qA++qbeoaWkyPRIHaR9oemiVaCHN");
//		lmap.put("uid","10000");
//		lmap.put("usid","b:BCSQOMKSQOMKSQOM:100:BoOBW/KqfUQdkmyDYF14E/g68w1tiZFFNA9xGW2AMCY=");
		 signkey = calcSign(lmap, "9a256fd6bae55aaba4ea7446869c3968");
		System.out.println(signkey);
	}
	
	
	
	
}
