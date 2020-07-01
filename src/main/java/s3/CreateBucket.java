package s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CreateBucketRequest;

public class CreateBucket {
	 public static void main(String[] args) {
		//region
		String clientRegion = "us-east-1";
		//bucketname
		String bucketName ="mybucketranjit291215";
		try {
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withCredentials(new ProfileCredentialsProvider())
					.withRegion(clientRegion)
					.build();
			if(!s3Client.doesBucketExistV2(bucketName)) {
				s3Client.createBucket(new CreateBucketRequest(bucketName));
				String bucketLocation = s3Client.getBucketLocation(bucketName);
				System.out.println("Bucket Location ::" +bucketLocation);
			}else {
				System.out.println("The bucket name ::"+bucketName+" exists. use another bucket name.");
			}
		}catch(AmazonServiceException e) {
			e.printStackTrace();
		}catch(SdkClientException e) {
			e.printStackTrace();
		}
	}
}
