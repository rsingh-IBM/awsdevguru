package s3;

import java.util.Iterator;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class DeleteObject {

	public static void main(String[] args) {
		String clientRegion = "us-east-1";
		String bucketName ="mybucketranjit291215";
		try {
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withCredentials(new ProfileCredentialsProvider())
					.withRegion(clientRegion)
					.build();
			//s3Client.deleteBucket(bucketName);
			ObjectListing objectListing = s3Client.listObjects(bucketName);
			while (true) {
				Iterator<S3ObjectSummary> objIter = objectListing.getObjectSummaries().iterator();
				while(objIter.hasNext()) {
					s3Client.deleteObject(bucketName, objIter.next().getKey());
				}
				if(objectListing.isTruncated()) {
					objectListing = s3Client.listNextBatchOfObjects(objectListing);
					}else {
						break;
					}
			}
			s3Client.deleteBucket(bucketName);
		}catch(AmazonServiceException e) {
			e.printStackTrace();
		}catch(SdkClientException e) {
			e.printStackTrace();
		}

	}
}

