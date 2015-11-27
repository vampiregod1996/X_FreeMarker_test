package x.play.freemarker.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class XTestMain {

	
	public static void main(String[] args) {
		Writer out=null;
		String gAME_VERSION = "1";
		String tAG_VERSION = "195";
		String channelName = "ZHANGYUE";
		String cHANNEL_ID = "3058";
		String mEIDA_CHANNEL = "2011552002";
		
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		try {
			cfg.setDirectoryForTemplateLoading(new File("./templates"));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			
			Map<String, Object> root = new HashMap<String, Object>();
			XMTLBBConfig xConfig = XTestMain.getConfigBean(channelName, cHANNEL_ID, gAME_VERSION, tAG_VERSION, mEIDA_CHANNEL);
			root.put("XMTLBB", xConfig);
			
			File target = new File("./target/"+cHANNEL_ID+"/config.properties");
			target.getParentFile().mkdirs();
			
			Template temp = cfg.getTemplate("config.ftl");
			out = new OutputStreamWriter(new FileOutputStream(target), "utf-8");
			temp.process(root, out);
			
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static XMTLBBConfig getConfigBean(String channelName, String cHANNEL_ID, String gAME_VERSION, String tAG_VERSION, String mEIDA_CHANNEL) {
		XMTLBBConfig xConfig = new XMTLBBConfig();
		xConfig.setCHANNEL_STRING(channelName);
		xConfig.setCHANNEL_STRING_LOWERCASE(channelName.toLowerCase());
		xConfig.setCHANNEL_ID(cHANNEL_ID);
		xConfig.setGAME_VERSION(gAME_VERSION);
		xConfig.setTAG_VERSION(tAG_VERSION);
		xConfig.setMEIDA_CHANNEL(mEIDA_CHANNEL);
		
		
		return xConfig;
	}
	
	
	
	
}
