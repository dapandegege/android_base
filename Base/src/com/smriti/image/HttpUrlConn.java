//package com.smriti.image;
//
//import java.io.ByteArrayOutputStream;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Set;
//
//import android.util.Log;
//
//
//public class HttpUrlConn {
//    //标签
//    private String TAG= "HttpUrlConn";
//    //HttpURLConnection请求
//    private HttpURLConnection myhttpconnect;
//
//    
//    String end = "/r/n";  
//    String start = "--";  
//    String boundary = "*****";  
//    
//    
//    //数据输出管道
//    private DataOutputStream ds=null;
//    //文件总大小
//    private double allFilesize=0;
//    //当前传输了多少
//    private double curr_ret=0;
//
//    public  HttpURLConnection getHttpUrlConnInstances(
//		String path,
//        HashMap<String,String> paramString,
//        HashMap<String, File> paramFiles,
//        ConfigParamters config)
//    {
//        try {
//         URL url=new URL(path);      
//        myhttpconnect=(HttpURLConnection)
//        url.openConnection();
//        /* 允许Input、Output，post中不使用Cache */
//        myhttpconnect.setDoInput(config.isDoInput());
//        myhttpconnect.setDoOutput(config.isDoOutput());
//        myhttpconnect.setUseCaches(config.isUseCaches());
//        
//        /* 设置传送的method=POST */ 
//        myhttpconnect. setRequestMethod(config.getUseMethod());
//        
//        /*设置属性 setRequestProperty */       
//        myhttpconnect.setRequestProperty(Connection, Keep-Alive);
//            myhttpconnect.setRequestProperty(Charset, UTF-8);
//            myhttpconnect.setRequestProperty(Content-Type, multipart/form-data;boundary= + boundary);
//            
//           
//            ds = new DataOutputStream( myhttpconnect.getOutputStream()); /* 设置DataOutputStream */
//            
//            
//            /*把普通参数写到服务器*/
//            if(null!=paramString && mString.size()>0){
//                writeStringParam(ds, paramString);
//            }
//            
//             /*把文件参数写到服务器*/
//            if(null!=paramFiles&¶mFiles.size()>0){
//                writeFileParam(ds, paramFiles);
//            }
//            //添加结尾标志
//            paramsEnd(ds);
//            
//            
//            /*
//             *返回
//             */
//        if(myhttpconnect.getResponseCode()
//                    ==HttpURLConnection.HTTP_OK){
//             InputStream is = 
//                     myhttpconnect.getInputStream();
//            String successMsg=InputStremtoString(is);
//           
//            
//        }catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//         
//        }
//        return myhttpconnect;
//    }
//    
///**
//* 把字节输入流转换成字符串
//* @return
//*/
//private String InputStremtoString(InputStream is)throws Exception{
//        int ch;
//        StringBuffer b = new StringBuffer();
//        byte[] bt=new byte[1024];
//        while ((ch = is.read(bt)) != -1)
//        {   
//            b.append(new String(bt, 0, ch, UTF-8));
//        }
// 
//        return b.toString();
//    }
// 
//HttpPost post = new HttpPost(
//        "http://veikr.com/myupload/upload.php");
//File file = new File("/mnt/sdcard/infor.txt");
//MultipartEntity multipart = new MultipartEntity();
//multipart.addPart("name", new StringBody("veikr.com"));
//multipart.addPart("file", new FileBody(file));
//HttpClient client = new DefaultHttpClient();
//post.setEntity(multipart);
//HttpResponse response = client.execute(post);
//return response;
//
///**
//* 传普通参数(第二种方法)
//* @param parm:参数
//*/
//	private void writeStringParam(DataOutputStream ds, HashMap<String, String> params)
//	{
//        try{
//            Set<String> keySet = params.keySet();
//            for (Iterator<String> it =keySet.iterator(); it.hasNext();){
//            	
//            	String name = it.next();
//                
//                String value = params.get(name);
//                
//                ds.writeBytes(start + boundary + end);
//                ds.writeBytes("Content-Disposition:form-data;name=" + name + end);
//                ds.writeBytes(new String(value.getBytes(),"utf-8")+ end);
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
// 
///***
//* 传输文件
//* @param ds ：数据传输通道
//* @param params ：参数
//*/
//	private void writeFileParam(DataOutputStream ds,HashMap<String, File> params){
//        try{
//            Set<String> keySet = params.keySet();
//            getFilesSize(params);
//            for (Iterator<String> it =keySet.iterator(); it.hasNext();){
//                String name = it.next();
//                File value = params.get(name);
//                ds.writeBytes(start + boundary + end); 
//                ds.writeBytes(Content-Disposition: form-data; name= + name + ; filename= + URLEncoder.encode(value.getName()) + +end);
//                ds.writeBytes(Content-Type:  + getContentType(value) + end);
//                ds.writeBytes(end);
//                ds.write(getBytes(value));
//                ds.writeBytes(end);
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
///**
//* 获取要传输文件总共大小
//* @param params
//*/
//private void getFilesSize(HashMap<string, file=""> params){
//        for(Entry<string, file=""> data:params.entrySet()){
//            File f=data.getValue();
//            allFilesize+=f.length();
//        }
//        Log.i(TAG, AllFileSize:+allFilesize);
//    }
///**
//* 获取文件的上传类型，图片格式为image/png,image/jpg等。非图片为application/octet-stream
// * @param f 文件
// * @return
// * @throws Exception
//*/
//private String getContentType(File f) throws Exception{
//        return application/octet-stream;
//}
// 
///**
//* 添加结尾数据  
//* @param ds:数据传输通道
//*  * @throws Exception
//*/
//private void paramsEnd( DataOutputStream ds) throws Exception { 
//        ds.writeBytes(twoHyphens + boundary + 
//                                twoHyphens + end);  
//        ds.writeBytes(end);       
//    } 
// 
//    /**
//     * 把文件转换成字节数组
//     * @param f:文件
//     * @return
//     * @throws Exception
//     */
//private byte[] getBytes(File f) throws Exception{
//    FileInputStream in = new FileInputStream(f);
//    ByteArrayOutputStream out = new ByteArrayOutputStream();
//        byte[] b = new byte[1024];
//        int n;
//        while ((n = in.read(b)) != -1)
//        {
//            out.write(b, 0, n);
//            if(allFilesize>0){
//                curr_ret+=n;
//                double level=(curr_ret/allFilesize)*100;
//                Log.i(result, +level);
//                if(uploadlinsener!=null){
//                    uploadlinsener.uploading(level);
//                }
//            }
//        }
//        in.close();
//       return out.toByteArray();
//    }
// 
//public void setUploadlinsener(UploadLinsener uploadlinsener) {
//        this.uploadlinsener = uploadlinsener;
//    }
//}