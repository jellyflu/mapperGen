package com.jelly.mapperGen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


public class GenDemo extends  JFrame  implements ActionListener{
	 
	 private static final long serialVersionUID = 4284020376229953894L;
	 private JLabel label1,label2,label3;//labe1 文本标签 1 2 3 
	 private JLabel label4;
	 private JTextField textField1  ,textField2;//文本输入框1 2 
	 private JTextField textField3;
	 private JScrollPane scrollPanel1 ,scrollPanel2;//滚动面板1   滚动面板2 
	 private  JTextArea textArea1 ,textArea2;//文本域1  文本域2
     private JButton  button1 ,button2 ,button3;// 按钮3个     生成xml  清空  保存到文件
     private JButton button4;//按钮4  生成bean代码
     private JButton button5;// 清空textArea1 内容
     private JButton  button6;//生成mapper接口
     
     private String savedir;//保存文件目录
	
     public static void main(String[] args){
    	 GenDemo demo =new GenDemo();
    	 demo.init();
     }
     public void init(){
  	    this.setTitle("GenMapper工具");
  	  //  this.setBounds(0, 0, 600, 550);
  	    this.setBounds(0, 0, 700, 600);
	    this.setLayout(null);
	 label1=new JLabel("表名:");
   	 label2=new JLabel("实体名(简单):");
   	 label3=new JLabel("表字段(列)声明");
   	 label4=new JLabel("mapper命名空间:");
   	 
        label1.setBounds(20, 20, 80, 20);
        label2.setBounds(20, 50, 80, 20);  label4.setBounds(205, 50, 100, 20);
        label3.setBounds(20, 80, 100, 20);
       
        button5=new JButton("清空");
        button5.setBounds(130, 80, 80, 25);
   	 this.add(label1);
   	 this.add(label2);
   	 this.add(label3);
   	 this.add(label4);
   	 this.add(button5);
   	 
   	 
   	 textField1=new JTextField();
   	 textField2=new JTextField();
   	 textField3=new JTextField();
   	 textField1.setBounds(100, 20, 100, 20);
   	 textField2.setBounds(100, 50, 100, 20); 
   	 textField3.setBounds(310, 50, 230, 20);
   	 this.add(textField1);
   	 this.add(textField2);
   	 this.add(textField3);
         
   	 textArea1=new JTextArea();
     textArea2=new JTextArea();
   	
   	 scrollPanel1=new JScrollPane(textArea1);
   	 scrollPanel1.setBounds(20, 110, 640, 150);
     scrollPanel2=new JScrollPane(textArea2);
     scrollPanel2.setBounds(20, 310, 640, 210);
     button1=new JButton("生成xml");
     button2=new JButton("清空");
     button3=new JButton("保存到文件");
     button4=new JButton("生成bean");
     button6=new JButton("生成接口");
     
     
     
     button1.setBounds(20, 270, 100, 25);
     button2.setBounds(130, 270, 80, 25);
     button3.setBounds(220, 270, 100, 25);
     button4.setBounds(330, 270, 100, 25);
     button6.setBounds(440, 270, 100, 25);
     
     this.add(button1);
     this.add(button2);
     this.add(button3);
     this.add(button4);
     this.add(button6);
   	 
   	 this.add(scrollPanel1);
   	 this.add(scrollPanel2);
   	//this.setResizable(false);
   	this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JFUtils.positionCenter(this);
    
      initEvent();//初始化事件    注册事件处理程序
     }
     public void initEvent(){
    	 button1.addActionListener(this);
    	 button1.setActionCommand("generateXML");
    	 button2.addActionListener(this);
    	 button2.setActionCommand("clearXML");
    	 button3.addActionListener(this);
    	 button3.setActionCommand("saveFile");
    	 button4.setActionCommand("generateBean");
    	 button4.addActionListener(this);
    	 
    	 button5.setActionCommand("clearDeclare");
    	 button5.addActionListener(this);
    	 
    	 button6.setActionCommand("generateInter");
    	 button6.addActionListener(this);
    	 textArea2.addKeyListener(new KeyListener() {//键盘监听事件
    			@Override
    			public void keyTyped(KeyEvent e) {
    			}
    			@Override
    			public void keyReleased(KeyEvent e) {
    			}
    			@Override
    			public void keyPressed(KeyEvent e) {
    				if (e.isControlDown()&& e.getKeyCode() == KeyEvent.VK_S) {
    						//System.out.println("ctrl +S has pressed");
    					saveFile();//保存
    			    }
    			}
    		});
     }
	@Override
	public void actionPerformed(ActionEvent e) {
		 String command= e.getActionCommand();
			switch(command){
			case "generateXML":
				generateXML();
				break;
			case "clearXML":
				clearXML();
				break;
			case "saveFile":
				saveFile();
					break;
			case "generateBean":
				generateBean();
				break;
			case "clearDeclare":
				clearDeclare();
				break;
			
			case  "generateInter":
				generateInter();
				break;
			
			}
	}

	 public   String firstLetterLowercase(String str){
			String s= str.substring(1);
			char letter=Character.toLowerCase(str.charAt(0));
			return    letter+s;
	 }
	//生成mapper 接口
	private void generateInter() {
		String entityName=this.textField2.getText();//实体名(简单名称) Book
	   	String fieldsDeclare=this.textArea1.getText();
		 String fieldTypeRegex="(?<=`)\\s+[\\w\\(\\)]+";
		 
		 if(fieldsDeclare==null||fieldsDeclare.trim().equals("")){
				JOptionPane.showMessageDialog(this, "表字段(列)声明不能为空！"); return ;
			}
		 
		 if(entityName==null||entityName.trim().equals("")){
				JOptionPane.showMessageDialog(this, "实体名不能为空！"); return ;
			}
		
	       Pattern p2=Pattern.compile(fieldTypeRegex); 
	       Matcher m2=p2.matcher(fieldsDeclare);
	       List<String> fieldTypeList=new ArrayList<String>();
	       while(m2.find()){
	    	  String fieldType=   m2.group();
	    	  fieldTypeList.add(fieldType.trim());
	       }
	       String idJavaType="String";
	       if(fieldTypeList.get(0).startsWith("int")){
	    	  // idType="int";
	    	   idJavaType="int";
	       } 
	       String idListType=null;
	       if(idJavaType.equals("int")){
	    	   idListType="Integer";
	       }else{
	    	   idListType="String";
	       }
	       
	       String  mapperInterfaceName=entityName+"Mapper";
	       String  smallEntityName=firstLetterLowercase(entityName);
		
		 
		StringBuilder sb =new StringBuilder();                             
	sb.append(	"public interface "+mapperInterfaceName+" {	  \r\n   "  ) ;
	sb.append(	"	public List<"+entityName+"> findAll();          \r\n   "  );
	sb.append(	"	public int save("+entityName+" "+smallEntityName+");      \r\n   "  ) ;
	sb.append(	"	public int update("+entityName+" "+smallEntityName+");     \r\n   "  ) ;
	sb.append(	"	public int deleteById("+idJavaType+" id);        \r\n   "  ) ;
	sb.append(	"	public int deleteByIds("+idJavaType+"[] ids);       \r\n   "  )  ;
	sb.append(	"	public int deleteByIdList(List<"+idListType+"> idList);    \r\n   "  )  ;
	sb.append(  "    public  "+entityName+" findById("+idJavaType+" id);       \r\n   ")   ;
	sb.append(  "    public  List<"+entityName+"> findByIds("+idJavaType+"[] ids);     \r\n   ")  ;
	sb.append(  "    public  List<"+entityName+"> findByIdList(List<"+idJavaType+"> idList); \r\n   ")  ;
	
	sb.append(  "	public  List<"+entityName+">  find"+entityName+"ListPage(Map<String,Object> param);\r\n  " );
	sb.append(  "	public  int find"+entityName+"CountPage(Map<String,Object> param);   \r\n  " );
	     sb.append("\r\n");
	sb.append(  "	public  List<"+entityName+">  findListByParamMap(Map<String,Object> param);  \r\n  " );
	

	
    sb.append(  "} " );
    
    textArea2.setText(sb.toString());
		
	}
	public void generateXML(){
		String tableName=this.textField1.getText();//表名 t_book
		String entityName=this.textField2.getText();//实体名(简单名称) Book
		String namespace=this.textField3.getText();//命名空间  com.jelly.mvnweb2.mapper.BookMapper
		String fieldsDeclare=this.textArea1.getText();
		if(tableName==null||tableName.trim().equals("")){
			JOptionPane.showMessageDialog(this, "表名不能为空！"); return ;
		}
		if(entityName==null||entityName.trim().equals("")){
			JOptionPane.showMessageDialog(this, "实体名不能为空！"); return ;
		}
		if(namespace==null||namespace.trim().equals("")){
			JOptionPane.showMessageDialog(this, "mapper命名空间不能为空！"); return ;
		}
		if(fieldsDeclare==null||fieldsDeclare.trim().equals("")){
			JOptionPane.showMessageDialog(this, "表字段(列)声明不能为空！"); return ;
		}
		
		 String fieldTypeRegex="(?<=`)\\s+[\\w\\(\\)]+";
	       Pattern p2=Pattern.compile(fieldTypeRegex); 
	       Matcher m2=p2.matcher(fieldsDeclare);
	       List<String> fieldTypeList=new ArrayList<String>();
	       while(m2.find()){
	    	  String fieldType=   m2.group();
	    	  fieldTypeList.add(fieldType.trim());
	       }
	       String idType="string";
	       if(fieldTypeList.get(0).startsWith("int")){
	    	   idType="int";
	       } 
 
		
       String fieldRegex="(?<=`)\\w+(?=`)";	
       Pattern p=Pattern.compile(fieldRegex);  
       Matcher m=p.matcher(fieldsDeclare);
       List<String> fieldList=new ArrayList<String>();
       while(m.find()){
    	   String field=m.group();
    	   fieldList.add(field);
       }
       
		StringBuilder sb=new StringBuilder();
	 
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n")
			.append("<!DOCTYPE mapper \r\n")
			.append("PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \r\n")
			.append("\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n");
		
		sb.append(" <mapper namespace=\""+namespace+"\" > "+ "\r\n");
	 
		 
		String  resultMapStr=buildResultMap(fieldList,entityName);
		sb.append(resultMapStr);
		
	 
		sb.append("   <insert id=\"save\" parameterType=\""+entityName+"\"> \r\n");//save 语句
		String insertCols=  buildInsertCols(fieldList);
		sb.append("       INSERT INTO "+tableName+"("+insertCols+") \r\n");
		String insertVals=buildInsertVals(fieldList);
		sb.append("       VALUES("+insertVals+") \r\n");
		sb.append("   </insert>\r\n");
		
		sb.append("   <select id=\"findAll\" resultType=\""+entityName+"\">\r\n ");  //findAll  语句
		sb.append("       SELECT  * FROM "+tableName+" \r\n");
		sb.append("   </select> \r\n");
		
		sb.append("   <update id=\"update\" parameterType=\""+entityName+"\"> \r\n");// update
		sb.append("       update  "+tableName+" t "+"\r\n");
		sb.append("      <set>\r\n");
		String updateSetIf=buildUpdateSetIf(fieldList,fieldTypeList);
		sb.append(updateSetIf);
		sb.append("     </set>\r\n");
		sb.append("     where t."+fieldList.get(0)+"=#{"+fieldList.get(0)+"} \r\n");
		sb.append("   </update>\r\n");
		
		sb.append("   <select id=\"findById\" parameterType=\""+idType+"\" resultType=\""+entityName+"\"> \r\n");//findById
		sb.append("      SELECT  * FROM `"+tableName+"` t WHERE t.`"+fieldList.get(0)+"`=#{_parameter} \r\n ");
		sb.append("   </select> \r\n");
		
		sb.append("   <delete id=\"deleteById\" parameterType=\""+idType+"\">  \r\n");//deleteById
		sb.append("      DELETE  FROM  "+tableName+"    WHERE "+fieldList.get(0)+"=#{_parameter} \r\n");
		sb.append("   </delete> \r\n");
		
		                                                                   
		sb.append("  <delete id=\"deleteByIdList\" >    \r\n  ");
	    sb.append("    DELETE  FROM "+tableName+"    \r\n  ");
	    sb.append("    WHERE  "+fieldList.get(0)+" IN      \r\n  ");
		sb.append("	   <foreach collection=\"list\" index=\"index\" item=\"id\" open=\"(\"  \r\n  ");
		sb.append("		  close=\")\" separator=\",\">  \r\n  ");
		sb.append("		 #{id}   \r\n  ");
		sb.append("	   </foreach>    \r\n  ");
	    sb.append(" </delete>  \r\n  ");
	    
		sb.append("  <delete id=\"deleteByIds\" >    \r\n  ");
	    sb.append("    DELETE  FROM "+tableName+"    \r\n  ");
	    sb.append("    WHERE  "+fieldList.get(0)+" IN      \r\n  ");
		sb.append("	   <foreach collection=\"array\" index=\"index\" item=\"id\" open=\"(\"  \r\n  ");
		sb.append("		  close=\")\" separator=\",\">  \r\n  ");
		sb.append("		 #{id}   \r\n  ");
		sb.append("	   </foreach>    \r\n  ");
	    sb.append(" </delete>  \r\n  ");
	    
                                                                                 
	   sb.append("  <select id=\"findByIdList\"  resultType=\""+entityName+"\">   \r\n ") ;
		sb.append("	SELECT * FROM `"+tableName+"` a      \r\n ") ;
		sb.append("	WHERE a.`"+fieldList.get(0)+"` IN  \r\n ") ;
		sb.append("	<foreach collection=\"list\" index=\"index\" item=\"id\" open=\"(\" \r\n ") ;
		sb.append("		close=\")\" separator=\",\">                                  \r\n ") ;
		sb.append("		#{id}                                                   \r\n ") ;
		sb.append("	 </foreach>                                                    \r\n ") ;
	    sb.append("  </select>  \r\n ");
	   
	    sb.append("  <select id=\"findByIds\"  resultType=\""+entityName+"\">   \r\n ") ;
		sb.append("	SELECT * FROM `"+tableName+"` a      \r\n ") ;
		sb.append("	WHERE a.`"+fieldList.get(0)+"` IN  \r\n ") ;
		sb.append("	<foreach collection=\"array\" index=\"index\" item=\"id\" open=\"(\" \r\n ") ;
		sb.append("		close=\")\" separator=\",\">                                  \r\n ") ;
		sb.append("		#{id}                                                   \r\n ") ;
		sb.append("	 </foreach>                                                    \r\n ") ;
	    sb.append("  </select>  \r\n ");
	    
		sb.append("  <select id=\"find"+entityName+"ListPage\" parameterType=\"map\" resultType=\""+entityName+"\">\r\n  ");
		sb.append("		 SELECT * FROM `"+tableName+"` a                                          \r\n  "); 
		sb.append("		     <where>                                                              \r\n  ");
		sb.append("		     </where>                                                             \r\n  ");
		sb.append("		     <if test=\"sortName!=null and sortName!='' and sortOrder!=null and sortOrder!='' \" >                   \r\n  ");
		sb.append("		           ORDER BY ${sortName} ${sortOrder}                              \r\n  ");
		sb.append("		     </if>        \r\n  ");
		sb.append("		  LIMIT  #{startNum},#{pageSize}                                       \r\n  ");
		sb.append("	  </select>                                                                   \r\n  ");
		sb.append("	 <select id=\"find"+entityName+"CountPage\" parameterType=\"map\" resultType=\"int\">     \r\n  ");
		sb.append("		   SELECT count(1) FROM `"+tableName+"` a                                    \r\n  ");
		sb.append("		        <where>       \r\n  ");
		sb.append("		        </where>     \r\n  ");
		sb.append("	 </select> \r\n ");
		sb.append("	 <select id=\"findListByParamMap\" parameterType=\"map\" resultType=\""+entityName+"\"> \r\n  ");
		sb.append(" 		 SELECT * FROM `"+tableName+"` a                                              \r\n  ");
		sb.append(" 		     <where>                                                            \r\n  ");
		sb.append(" 		     </where>                                                           \r\n  ");
		sb.append(" 		     <if test=\"sortName!=null and sortName!='' and sortOrder!=null and sortOrder!=''\" >                 \r\n  ");
		sb.append(" 		           ORDER BY ${sortName} ${sortOrder}                            \r\n  ");
		sb.append(" 		     </if>                                                          \r\n  ");
		sb.append("   </select>        \r\n");
	    sb.append("</mapper> \r\n");    
		textArea2.setText(sb.toString());
	}
	//生成bean 
	public void generateBean(){
		String fieldsDeclare=this.textArea1.getText();
		if(fieldsDeclare==null||fieldsDeclare.trim().equals("")){
			JOptionPane.showMessageDialog(this, "表字段(列)声明不能为空！"); return ;
		}
    
       String fieldRegex="(?<=`)\\w+(?=`)";	
       Pattern p=Pattern.compile(fieldRegex);  
       Matcher m=p.matcher(fieldsDeclare);
       List<String> fieldList=new ArrayList<String>();
       while(m.find()){
    	   String field=m.group();
    	   fieldList.add(field);
       }
       
       String fieldTypeRegex="(?<=`)\\s+[\\w\\(\\)]+";
       Pattern p2=Pattern.compile(fieldTypeRegex); 
       Matcher m2=p2.matcher(fieldsDeclare);
       List<String> fieldTypeList=new ArrayList<String>();
       while(m2.find()){
    	  String fieldType=   m2.group();
    	  fieldTypeList.add(fieldType.trim());
       }
       
    StringBuilder sb=new StringBuilder();  
       
       for(int i=0;i<fieldList.size();i++){
    	   String field=fieldList.get(i);
    	   String fieldType=fieldTypeList.get(i);
    	 
    	    if(fieldType.contains("int")){
    	    	 sb.append("private Integer "+ field +";\r\n");
    	    }else if(fieldType.equals("double")||fieldType.equals("float")){
    	    	 sb.append("private Double "+ field +";\r\n");
    	    }else if(fieldType.startsWith("decimal")){
    	    	 sb.append("private String "+ field +";\r\n");
    	    }else if(fieldType.endsWith("blob")){
    	    	 sb.append("private byte[] "+ field +";\r\n");
    	    }else{
    	    	sb.append("private String "+ field +";\r\n");
    	    } 
       }
		this.textArea2.setText(sb.toString()); 
	}
	public void clearXML(){
		this.textArea2.setText("");
	}
	public void clearDeclare(){
		this.textArea1.setText("");
		this.textField1.setText("");
		this.textField2.setText("");
		this.textField3.setText("");
	}
	
	public void saveFile(){
		// JOptionPane.showMessageDialog(this, "saveFile");
	BufferedWriter writer=null;
		try {
			 JFileChooser wjsave=null;
			 if(savedir!=null&&savedir.trim().length()>0){
				 wjsave=new JFileChooser(savedir);
			 }else{
				 wjsave=new JFileChooser();
			 }
			 wjsave.setDialogTitle("保存到文件");
			 
				FileNameExtensionFilter fef1=new FileNameExtensionFilter("文本文件","txt");
				FileNameExtensionFilter fef2=new FileNameExtensionFilter("xml文件","xml");
			 
				wjsave.addChoosableFileFilter(fef1);
				wjsave.addChoosableFileFilter(fef2);
			 
			 wjsave.setDialogType(JFileChooser.SAVE_DIALOG);
			int value= wjsave.showSaveDialog(null);
			 wjsave.setVisible(true);
			 if(value==JFileChooser.APPROVE_OPTION){//用户点击了保存
				   File savefile= wjsave.getSelectedFile();// 保存的文件
				   savedir= savefile.getParent();//将文件目录保存到成员变量
				 //  writer=new BufferedWriter(new FileWriter(savefile));
				  writer = new BufferedWriter(new FileWriter(savefile));
				  String result=textArea2.getText();
			
				  writer.write(result);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {//关流操作
            try {
                if (writer != null) {
                	writer.close();
                }
            }
            catch (Exception e) {
                  System.out.println("BufferedWriter close IOException" + e.getStackTrace());
            }
      }
	}
	
	
	/**
	 * 生成  xxxResultMap 语句 中 id、result 列表  
	 * @param fieldList  字段列表
	 * @return String
	 * @author jelly
	 *
	 */
	private  String buildResultMap(List<String> fieldList,String entityName){
		
 
	
		StringBuilder sb=	new StringBuilder();
		sb.append("<resultMap id=\""+entityName+"ResultMap\" type=\""+entityName+"\" >").append("\r\n");
		sb.append("  <id column=\""+fieldList.get(0).toUpperCase()+"\"  property=\""+fieldList.get(0)+"\"/>").append("\r\n");
		for(int i=1;i<fieldList.size();i++){
			sb.append("  <result column=\""+fieldList.get(i).toUpperCase()+"\" property=\""+fieldList.get(i)+"\"  />").append("\r\n");
		}
		sb.append("</resultMap>").append("\r\n");
		return sb.toString();
	}
	
	/**
	 * 生成insert 语句中的  column 列表
	 * @param fieldList
	 * @return
	 * @return String
	 * @author jelly
	 *
	 */
	private String buildInsertCols(List<String> fieldList) {
			//`id`,`name`,`author`,`price`,`des`,`publisher`,`publishtime`,`createtime`,`imgBytes`,`htmlText`
		StringBuilder sb=	new StringBuilder();
			for(int i=0;i<fieldList.size();i++){
				 String field=  fieldList.get(i);
				if(i==fieldList.size()-1){
					sb.append(addBacktick(field));
				}else{
					sb.append(addBacktick(field)).append(",");
				}
			}
		return sb.toString();
	}
	
	/**
	 * 生成insert 语句中的  value 列表
	 * @param fieldList
	 * @return
	 * @return String
	 * @author jelly
	 *
	 */
	private String buildInsertVals(List<String> fieldList) {
	    //#{id},#{name},#{author},#{price}, #{xxx}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<fieldList.size();i++){
			 String field=  fieldList.get(i);
			if(i==fieldList.size()-1){
				sb.append("#{"+field+"}");
			}else{
				sb.append("#{"+field+"}").append(",");
			}
		}
	    return sb.toString();
	}
	private String addBacktick(String s){//添加反撇号
		return "`"+s+"`";
	}
	
	/**
	 * 生成update  中的 if 列表 
	 * @param fieldList
	 * @return
	 * @return String
	 * @author jelly
	 */
	private String buildUpdateSetIf(List<String> fieldList, List<String> fieldTypeList) {
		 
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<fieldList.size();i++){
			 String field=  fieldList.get(i);
			 String fieldType=fieldTypeList.get(i);
			 if(fieldType.contains("int")){
				 sb.append("      <if test=\""+field+"!=null \" >"+"\r\n");
			 }else{
				 sb.append("      <if test=\""+field+"!=null and "+field+"!='' \" >"+"\r\n");
			 }
			 
			 sb.append("        t."+field+"=#{"+field+"}, \r\n");
			 sb.append("      </if>"+"\r\n");
		}
		
		return sb.toString();
	}
	
}
