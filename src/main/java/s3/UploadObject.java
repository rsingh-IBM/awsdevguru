package s3;

import java.io.File;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class UploadObject {

	public static void main(String[] args) {
		
		String clientRegion = "us-east-1";
		String bucketName ="mybucketranjit291215";
		String stringObjKeyName = "dev/test";        
		String fileObjKeyName = "sit/test";        
		// local file
		String fileName = "C:\\Users\\RanjitSingh\\Documents\\aws\\helloaws.txt";
		try {
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withCredentials(new ProfileCredentialsProvider())
					.withRegion(clientRegion)
					.build();
			s3Client.putObject(bucketName, stringObjKeyName, "uploaded string object");
			
			PutObjectRequest request = new PutObjectRequest(
					bucketName,fileObjKeyName,new File(fileName));
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType("plain/text");
			metadata.addUserMetadata("x-amz-meta-title", "studyguide");
			request.setMetadata(metadata);
			s3Client.putObject(request);
		}catch(AmazonServiceException e) {
			e.printStackTrace();
		}catch(SdkClientException e) {
			e.printStackTrace();
		}
	}
}
