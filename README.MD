# java开发的mapper图形化生成工具

## 一、如何运行

下载 mapperGen.jar ,双击它或者使用java命令 java -jar mapperGen.jar 即可。

运行截图：

![截图](https://raw.githubusercontent.com/jellyflu/pub_resouces/master/mapperGen/5.png)

#二、支持特性

根据mysql表生成实体bean  
根据mysql表生成mapper接口  
根据mysql表生成mapper.xml  



支持mysql表的字段中下划线转为bean中的驼峰命名属性，例如:

| 表的字段  | bean的属性 |
| --------- | ---------- |
| USER_NAME | userName   |
| user_name | userName   |
| User_NAme | userName   |
| userName  | userName   |

SqlMapConfig.xml中配置开启骆驼峰名称映射

```xml
<!-- mapper中带下划线字段可映射为bean中的驼峰格式的属性,无须再定义ResultMap了-->
<setting name="mapUnderscoreToCamelCase" value="true"/>
```
