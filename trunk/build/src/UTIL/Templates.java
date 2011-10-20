package UTIL;

public class Templates {
	
	
	
	public static void main(String[] args) {
		String str="hi #username#, you have a message. the content #content#";
		String username="luan",content="dfdjk";
		String[] arrayName={"#username#","#content#"};	
		String[] arrayContent={username,content};	
		
		if(arrayName.length==arrayContent.length){
			for(int i=0;i<arrayName.length;i++){
				int index=str.indexOf(arrayName[i]);
				if(index!=-1){
					str=str.replace(arrayName[i], arrayContent[i]);
				}
			}
		}
		
		System.out.println(str);
	}
}
