package CS610;
import java.io.*;
import java.util.Vector;



public class FileUtility {
	  
	  private File inputFile;  
	  private File outputFile;  
	  private InputStream inputStream;  
	  private OutputStream outputStream;  
	  
	  public FileUtility(String inputPath, String outputPath) throws FileNotFoundException{  
		     if (inputPath != null){
		    	 inputFile=new File(inputPath);
		    	 inputStream=new FileInputStream(inputFile);  
		     }
		     
		     if (outputPath !=null){
		    	 outputFile=new File(outputPath);               
		    	 outputStream=new FileOutputStream(outputFile);
		     }
      }  
	  
	  public void copy_ByBuffer() throws IOException{
		  
		  BufferedInputStream in = new BufferedInputStream(inputStream);  
		   
		   if (outputFile != null) {  
		       outputFile.createNewFile();  
		   }  
		   
		  BufferedOutputStream out = new BufferedOutputStream(outputStream);  
		  byte[] bb = new byte[1024];
		  int n=0;
		  while ((n = in.read(bb)) != -1) {  
		       out.write(bb, 0, n);
		  }  
		  out.close();
		  in.close();  

	  }
	  
	  
	  public String[] copy_ByLine() throws IOException {
		  
		    FileReader fr = new FileReader(inputFile);
		    LineNumberReader lr = new LineNumberReader(fr);
		    Vector v = new Vector();
		    String line = null;
		    while ((line = lr.readLine()) !=null){
		    	v.add(line.trim());
		    }
		    String[] result = new String[v.size()];
		    
		    v.toArray(result);
		    return result;
	  }
	  
	  
	  
	  
	  public void copy_ByWriteAndReadOnce() throws IOException{  
		         byte b[]=new byte[(int)inputFile.length()];  
		          inputStream.read(b);       //一次讀入 
		          inputStream.close();  
		          
		          if (inputFile.exists()){
		        	  boolean deleted = inputFile.delete();
		        	  if (deleted){
		        		  System.out.println("delete");
		        	  }else{
		        		  System.out.println("no delete");
		        	  }
		          }
		          
		          outputStream.write(b);   //一次寫入
		          inputStream.close();  
		          outputStream.close();  
      }
	  
	  public void copy() throws IOException{  
		      int temp=0;  
		          while((temp=inputStream.read())!=-1){  
		              outputStream.write(temp);  
		          }  
		          inputStream.close();  
		          outputStream.close();  
      }  
	  
	  
	  public boolean insertData_InFile_ByLineNo( int lineno, String lineToBeInserted) throws IOException{  
	
	     boolean isInserted = false;
	     File outFile = File.createTempFile("tmp_", ".tmp", this.getFileDirectory() );
	  
	      // 输入	     
	      BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
	  
	      // 输出
	      FileOutputStream fos = new FileOutputStream(outFile);
	      PrintWriter out = new PrintWriter(fos);
	  
	      // 保存一行数据
	      String thisLine;
	      // 行号从1开始
	      int i = 1;
	      while ((thisLine = in.readLine()) != null) {
	    	  // 如果行号等于目标行，则输出要插入的数据
	    	  if (i == lineno) {
	    		  out.println(lineToBeInserted);
	    		  isInserted = true;
	    	  }
	    	  // 输出读取到的数据
	    	  out.println(thisLine);
	    	  // 行号增加
	    	  i++;
	      }
	      out.flush();
	      out.close();
	      in.close();
	  
	      // 删除原始文件
	      inputFile.delete();
	      // 把临时文件改名为原文件名
	      outFile.renameTo(inputFile);
	      return isInserted;
	    }
	  
	  public boolean updateData_InFile( String propertyName, String value) throws IOException{  
			
	      boolean isUpdated = false;  
	      File outFile = File.createTempFile("tmp_", ".tmp", this.getFileDirectory() );	     	     
	      BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
	  
	      // 输出
	      FileOutputStream fos = new FileOutputStream(outFile);
	      PrintWriter out = new PrintWriter(fos);
	  
	      // 保存一行数据
	      String thisLine=null;
	      
	      while ((thisLine = in.readLine()) != null) {
	    	  // 如果行号等于目标行，则输出要插入的数据
	    	  if ( (thisLine.trim().indexOf(propertyName)) >=0 ){
	    		  out.println(propertyName+"= "+value);
	    		  isUpdated = true;
	    	  }else{
	    		  out.println(thisLine);
	    	  }
	    	  	    	 
	      }
	      out.flush();
	      out.close();
	      in.close();
	  
	      // 删除原始文件
	      inputFile.delete();
	      // 把临时文件改名为原文件名
	      outFile.renameTo(inputFile);
	      return isUpdated;
	    }

	  
	  public File getInputFile() {  
		          return inputFile;  
      } 
	  
       public void setInputFile(File inputFile) {  
		         this.inputFile = inputFile;  
	    }
       
       public File getOutputFile() {  
           return outputFile;  
       }
      
	   public void setOutputFile(File outputFile) {  
		          this.outputFile = outputFile;  
	   }  
	   
	   
	   public File getFileDirectory(){
		   
		   	String currentJavaJarFilePath = inputFile.getAbsolutePath();
			String currentRootDirectoryPath = currentJavaJarFilePath.replace(inputFile.getName(), "");
			if (currentRootDirectoryPath !=null){
				currentRootDirectoryPath = currentRootDirectoryPath.trim();
			}
			
			String path = null;
			
			
			if ( currentRootDirectoryPath.lastIndexOf("/") >=0){
				// in linux
				path = currentRootDirectoryPath.substring(0, currentRootDirectoryPath.lastIndexOf("/"));
			}else if ( currentRootDirectoryPath.lastIndexOf("\\") >=0){
				// in windows	
				path = currentRootDirectoryPath.substring(0, currentRootDirectoryPath.lastIndexOf("\\"));
		    }
			
			return new File(path);			
	   }
	   
	   public void finalize(){
		  
			   
			   if (inputStream !=null){
				   try{
					   inputStream.close();
				   }catch(Exception e){
					   e.printStackTrace();
					  
				   }
					   
			   }
				
			   if (outputStream !=null){
				   try{
					   outputStream.close();
				   }catch(Exception e){
					   e.printStackTrace();
					 
				   }
			   }
		
	   }
	   
       public static void main(String[] args){ 
    	   
    	   FileUtility fileUtility= null;
    	   
    	   try{
		      String inputPath="D:\\test\\digican.prop";  
		      String outputPath="D:\\test\\digican_tmp.prop" ;
		       fileUtility=new FileUtility(inputPath, outputPath); 
		      
		       
		      long start1=System.currentTimeMillis();  
		      ///fileUtility.copy_ByWriteAndReadOnce();  
		      
		      ///fileUtility.copy_ByBuffer();  
		      fileUtility.insertData_InFile_ByLineNo( 90 , "insert data  in 90 line");
		      
		      ///fileUtility.updateData_InFile( "serial_number", "a peter=testb d");
		      
		      long end1=System.currentTimeMillis();  
		      System.out.println("write file length:"+fileUtility.getInputFile().length()+", take:"+(end1-start1)+"ms");  
		      
		      
		       
		      ///fileUtility.getOutputFile().delete();  		      
		      /*
		      long start2=System.currentTimeMillis();  
		      fileUtility.copy();  
		      long end2=System.currentTimeMillis();  
		      System.out.println("边读边写复制文件大小为"+fileUtility.getInputFile().length()+"位花费时间为:"+(end2-start2)+"ms");
		      */
    	   }catch(Exception e){
    		   e.printStackTrace();
    	   }finally{
    		   fileUtility.finalize();
    	   }
  }  


}
