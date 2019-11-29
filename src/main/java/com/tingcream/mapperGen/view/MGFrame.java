package com.tingcream.mapperGen.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 主窗体
 * @author jelly
 */
public class MGFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	private JTextArea textArea_1;
	private JTextArea textArea_2;
	
	  private String savedir;//保存文件目录

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MGFrame frame = new MGFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);//主窗体居中
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MGFrame() {
		setResizable(false);
		setFont(new Font("微软雅黑", Font.PLAIN, 18));
		setTitle("mapperGen工具2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("表  名:");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("实体名(简单):");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("mapper命名空间:");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("表字段(列)声明:");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JButton btn_1 = new JButton("清空所有");
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				
				textArea_1.setText("");
				textArea_2.setText("");
				
			}
		});
		btn_1.setIcon(new ImageIcon(MGFrame.class.getResource("/com/tingcream/mapperGen/img/editclear_24px.png")));
		btn_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JButton btn_2 = new JButton("生成xml");
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateXml(e);
			}
		});
		btn_2.setIcon(new ImageIcon(MGFrame.class.getResource("/com/tingcream/mapperGen/img/xml_24px.png")));
		btn_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JButton btn_3 = new JButton("生成bean");
		btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateBean(e);
			}
		});
		btn_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JButton btn_4 = new JButton("生成接口");
		btn_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateInterface(e);
			}
		});
		btn_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JButton btn_5 = new JButton("保存文件");
		btn_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile(e);
			}
		});
		btn_5.setIcon(new ImageIcon(MGFrame.class.getResource("/com/tingcream/mapperGen/img/save_24px.png")));
		btn_5.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btn_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_5))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_3))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblNewLabel)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(scrollPane_1, Alignment.LEADING,GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
							.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(btn_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_2)
						.addComponent(btn_3)
						.addComponent(btn_4)
						.addComponent(btn_5))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		 textArea_2 = new JTextArea();
		 textArea_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		scrollPane_1.setViewportView(textArea_2);
		
		 textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		scrollPane.setViewportView(textArea_1);
		contentPane.setLayout(gl_contentPane);
	}

	
	//生成xml
	protected void generateXml(ActionEvent e) {
		String tableName=this.textField_1.getText();//表名 t_book
		String entityName=this.textField_2.getText();//实体名(简单名称) Book
		String namespace=this.textField_3.getText();//命名空间  com.jelly.mvnweb2.mapper.BookMapper
		String fieldsDeclare=this.textArea_1.getText();
		if(tableName==null||tableName.trim().equals("")){
			JOptionPane.showMessageDialog(null, "表名不能为空！"); return ;
		}
		if(entityName==null||entityName.trim().equals("")){
			JOptionPane.showMessageDialog(null, "实体名不能为空！"); return ;
		}
		if(namespace==null||namespace.trim().equals("")){
			JOptionPane.showMessageDialog(null, "mapper命名空间不能为空！"); return ;
		}
		if(fieldsDeclare==null||fieldsDeclare.trim().equals("")){
			JOptionPane.showMessageDialog(null, "表字段(列)声明不能为空！"); return ;
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
	       } else if(fieldTypeList.get(0).startsWith("bigint")) {
	    	   idType="long";
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
	 
		 
		
		
		//insertSelective语句 
		insertSelectiveSql(sb,tableName,entityName,fieldList);
		
	 
		
		//updateSelective语句
		updateSql(sb,tableName,entityName,fieldList);
		
		
		
		
		sb.append("   <select id=\"findAll\" resultType=\""+entityName+"\">\r\n ");  //findAll  语句
		sb.append("       SELECT  * FROM "+tableName+" \r\n");
		sb.append("   </select> \r\n");
		
		
		
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
		textArea_2.setText(sb.toString());
	
		
	}
	
	//生成Model  bean
	protected void generateBean(ActionEvent e) {
		String fieldsDeclare=this.textArea_1.getText();
		if(fieldsDeclare==null||fieldsDeclare.trim().equals("")){
			JOptionPane.showMessageDialog(null, "表字段(列)声明不能为空！");
			return ;
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
    	  
    	   String propertyName= toHumpCaseStr(fieldList.get(i)) ;//字段变为驼峰命名 -》属性
    	   String fieldType=fieldTypeList.get(i);
    	 
    	    if(fieldType.startsWith("int")){
    	    	 sb.append("private Integer "+ propertyName +";\r\n");
    	    }else if(fieldType.startsWith("bigint")) {
    	    	 sb.append("private Long "+ propertyName +";\r\n");
    	    }
    	    else if(fieldType.equals("double")||fieldType.equals("float")){
    	    	 sb.append("private Double "+ propertyName +";\r\n");
    	    }else if(fieldType.startsWith("decimal")){
    	    	 sb.append("private String "+ propertyName +";\r\n");
    	    }else if(fieldType.endsWith("blob")){
    	    	 sb.append("private byte[] "+ propertyName +";\r\n");
    	    }else{
    	    	sb.append("private String "+ propertyName +";\r\n");
    	    } 
       }
		this.textArea_2.setText(sb.toString()); 
		
	}
	
	//生成 mapper接口
	protected void generateInterface(ActionEvent e) {
		String entityName=this.textField_2.getText();//实体名(简单名称) Book
	   	String fieldsDeclare=this.textArea_1.getText();
		 String fieldTypeRegex="(?<=`)\\s+[\\w\\(\\)]+";
		 
		 if(fieldsDeclare==null||fieldsDeclare.trim().equals("")){
				JOptionPane.showMessageDialog(null, "表字段(列)声明不能为空！");
				return ;
			}
		 
		 if(entityName==null||entityName.trim().equals("")){
				JOptionPane.showMessageDialog(null, "实体名不能为空！");
				return ;
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
	       String  smallEntityName=firstCharacterLowercase(entityName);
		
		 
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
	    
	    textArea_2.setText(sb.toString());
		
	}
	
	
	//保存文件
	protected void saveFile(ActionEvent e) {
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
					  String result=textArea_2.getText();
				
					  writer.write(result);
				 }
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			finally {//关流操作
	            try {
	                if (writer != null) {
	                	writer.close();
	                }
	            }
	            catch (Exception e2) {
	                  System.out.println("BufferedWriter close IOException" + e2.getStackTrace());
	            }
	      }
			
		
	}
	
	
	
//	/**
//	 * 生成  xxxResultMap 语句 中 id、result 列表  
//	 * @param fieldList  字段列表
//	 * @return String
//	 * @author jelly
//	 *
//	 */
//	private  String buildResultMap(List<String> fieldList,String entityName){
//		
// 
//	
//		StringBuilder sb=	new StringBuilder();
//		sb.append("<resultMap id=\""+entityName+"ResultMap\" type=\""+entityName+"\" >").append("\r\n");
//		sb.append("  <id column=\""+fieldList.get(0).toUpperCase()+"\"  property=\""+fieldList.get(0)+"\"/>").append("\r\n");
//		for(int i=1;i<fieldList.size();i++){
//			sb.append("  <result column=\""+fieldList.get(i).toUpperCase()+"\" property=\""+fieldList.get(i)+"\"  />").append("\r\n");
//		}
//		sb.append("</resultMap>").append("\r\n");
//		
//		
//		sb.append("<resultMap id=\"BaseResultMap\" type=\""+entityName+"\" >").append("\r\n");
//		sb.append("  <id column=\""+fieldList.get(0)+"\"  property=\""+toHumpCaseStr(fieldList.get(0))+"\"/>").append("\r\n");
//		for(int i=1;i<fieldList.size();i++){
//			sb.append("  <result column=\""+fieldList.get(i)+"\" property=\""+toHumpCaseStr(fieldList.get(i))+"\" />").append("\r\n");
//		}
//		sb.append("</resultMap>").append("\r\n");
//		
//		return sb.toString();
//	}
	
	//bank_card_number --》 bankCardNumber  驼峰格式变量名
	//表的字段--》bean的属性
	private String toHumpCaseStr(String str) {
		
		String[] ss=str.split("_");
		if( ss.length==1) {  //表的字段名称 不含“_” ,直接返回原样
		   return str;	
		}
	 
		String str2=str.toLowerCase();//转为小写
		ss=str2.split("_");
		StringBuilder sb = new StringBuilder(ss[0]);
		for(int i=1;i<ss.length;i++) {
			sb.append(firstCharacterUppercase(ss[i]));//首字母大写
		}
		return sb.toString();
	}
	
	
	public String updateSql(StringBuilder sb,String tableName,String entityName, List<String> fieldList){
		
		sb.append("   <update id=\"update\" parameterType=\""+entityName+"\"> \r\n");// update
		sb.append("       update  "+tableName+"\r\n");
		sb.append("      <set>\r\n");
		
		
		for(int i=1;i<fieldList.size();i++) {
			String fieldName=fieldList.get(i);
			String propertyName= toHumpCaseStr(fieldName);
			 sb.append("      <if test=\""+propertyName+"!=null \" >"+"\r\n");
			 sb.append("         "+addBacktick(fieldName)+"=#{"+propertyName+"}, \r\n");
			 sb.append("      </if>"+"\r\n");
		}
		sb.append("     </set>\r\n");
		sb.append("     where "+fieldList.get(0)+"=#{"+toHumpCaseStr(fieldList.get(0)) +"} \r\n");
		sb.append("   </update>\r\n");
		
		return sb.toString();
	}
 
	
	//生成insertSelective语句 
	public String  insertSelectiveSql(StringBuilder sb,String tableName,String entityName, List<String> fieldList) {
		
		sb.append("   <insert id=\"save\" parameterType=\""+entityName+"\">\r\n");
		sb.append("       insert into "+tableName+"\r\n");
		sb.append("       <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\"> \r\n");
		for(String fieldName: fieldList) {//表单字段
			String propertyName= toHumpCaseStr(fieldName) ;//字段变为驼峰命名 -》属性
			sb.append("        <if test=\""+propertyName+" != null\"> \r\n");
			sb.append("          "+addBacktick(fieldName)+",\r\n");
			sb.append("        </if>\r\n");
		}
		sb.append("       </trim>\r\n");
		sb.append("       <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\"> \r\n");
		for(String fieldName: fieldList) {//表单字段
			String propertyName= toHumpCaseStr(fieldName) ;//字段变为驼峰命名 -》属性
			sb.append("        <if test=\""+propertyName+" != null\"> \r\n");
			sb.append("          #{"+propertyName+"},"+"\r\n");
			sb.append("        </if>\r\n");
		}
		sb.append("       </trim> \r\n");
		sb.append("   </insert>\r\n");
		return sb.toString();
		 
		
		
		
	}
 
	private String addBacktick(String s){//添加反撇号
		return "`"+s+"`";
	}
	
//	/**
//	 * 生成update  中的 if 列表 
//	 * @param fieldList
//	 * @return
//	 * @return String
//	 * @author jelly
//	 */
//	private String buildUpdateSetIf(List<String> fieldList, List<String> fieldTypeList) {
//		 
//		StringBuilder sb=new StringBuilder();
//		for(int i=1;i<fieldList.size();i++){
//			 String fieldName=  fieldList.get(i);//表字段名称
//			 
//			 String propertyName= toHumpCaseStr(fieldList.get(i)) ;//实体bean的属性名称
// 
//			 sb.append("      <if test=\""+propertyName+"!=null \" >"+"\r\n");
//			 sb.append("         "+fieldName+"=#{"+propertyName+"}, \r\n");
//			 sb.append("      </if>"+"\r\n");
//		}
//		
//		return sb.toString();
//	}
	
	
	
	
	/**
	 * 首字母小写
	 * @author jelly
	 * @param s
	 * @return
	 */
	private   String firstCharacterLowercase(String s){
		return s.substring(0, 1).toLowerCase()+s.substring(1);
    }
	
	/**
	 * 首字母大写
	 * @author jelly
	 * @param s
	 * @return
	 */
	private   String  firstCharacterUppercase(String s) {
		 
	  	return s.substring(0, 1).toUpperCase()+s.substring(1);
	}

	
	

}
