package com.qka;

class TmpCls{
	2.1.1	从cookie中获取merchantId,存入本地获取配置信息参数对象
	2.1.2	调用后台服务s003GetWxPubCfg,传入获取配置信息参数对象
	2.1.3	2.1.2服务返回结果存入本地变量:配置信息对象
	2.1.4	服务返回代码存入本地变量
	2.1.5	服务返回信息存入本地变量
	2.1.6	服务信息返回油企信息存入本地变量
	2.1.7	本地变量.返回代码=S01则表示未配置公众号
	2.1.7.1	页面元素油企ID=本地变量.油企信息.油企ID
	2.1.7.2	页面元素油企名称=本地变量.油企信息.油企名称
	2.1.8	本地变量.返回代码S99则表示服务异常,弹出提示
	2.1.9	如果返回为S00表示配置过公众号且查询成功
	2.1.9.1	服务返回帐号信息存入本地变量
	2.1.9.2	服务返回菜单信息存入本地变量
	2.1.9.3	页面元素油企ID=本地变量.油企信息.油企ID
	2.1.9.4	页面元素油企名称=本地变量.油企信息.油企名称
	2.1.9.5	页面元素appid=本地变量.帐号信息.appid
	2.1.9.6	页面元素原始id=本地变量.原始id
	2.1.9.7	页面元素应用密钥=本地变量.应用密钥
	2.1.9.8	页面元素域名验证文件=本地变量.验证文件名
	2.1.9.9	遍历菜单信息
	2.1.9.9.1	展示菜单名称
	2.1.9.9.2	如果菜单配置生效则展示选中,否则展示未选中
	
	
	
}