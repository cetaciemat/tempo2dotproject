package CetaCiemat.Jira;

import java.nio.charset.Charset;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

/**
 *
 * @author csuarez
 */
public class ApiUtils {
    public static String getCodedCredentials(String user, String password) {
        String credentials = user + ":" + password;
        byte[] encoded = Base64.encodeBase64(credentials.getBytes());
        return new String(encoded);
    }
    
    public static Integer extractTaskIdFromDotprojectUrl(String url) throws DotprojectFieldException {
        List<NameValuePair> args = URLEncodedUtils.parse((String) url, Charset.defaultCharset());
        Integer dotprojectTaskId = null;
        for (NameValuePair arg:args) {
            if (arg.getName().equals("task_id")) {
                dotprojectTaskId = Integer.valueOf(arg.getValue());
            }
        }
        
        if (dotprojectTaskId == null) {
            throw new DotprojectFieldException("Invalid DotProject task URL");
        }
        
        return dotprojectTaskId;
    }
}
