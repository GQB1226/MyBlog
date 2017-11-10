package cn.gqbit.myblog.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 七千牛图片上传
 */
@Component
public class QiniuPicUpload {

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucketName}")
    private String bucketName;

    @Value("${qiniu.basePath}")
    private String basePath;

    public String getUpToken(){
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucketName);
    }

    public HashMap<String,Object> upload(File filePath, String key){
        //华北
        HashMap<String,Object> result= new HashMap<>();
        Configuration cf=new Configuration(Zone.zone1());
        try {
            Response res= new UploadManager(cf).put(filePath,key,getUpToken());
            if (res.isOK()){
                result.put("success", 1);
                result.put("message", "上传成功");
                result.put("url", "http://"+basePath+"/"+key);
                return result;
            }
        } catch (QiniuException e) {
            result.put("success", 0);
            result.put("message", "上传失败："+e.getMessage());
            result.put("url", "");
        }
        return result;

    }
}
