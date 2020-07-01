package ec2;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

/**
 * Describe EC2 Instances
 * @author RanjitSingh
 *
 */
public class DescribeEC2Instance {

	public static void main(String[] args) {
		//region
		String clientRegion = "us-east-1";
		boolean done = false;
		AmazonEC2 ec2Client = AmazonEC2ClientBuilder.standard()
				.withCredentials(new ProfileCredentialsProvider())
				.withRegion(clientRegion)
				.build();
		DescribeInstancesRequest describeInstancesRequest = new DescribeInstancesRequest();
		while(!done) {
			DescribeInstancesResult response = ec2Client.describeInstances(describeInstancesRequest);
			for(Reservation reservation : response.getReservations()) {
				for(Instance instance : reservation.getInstances()) {
					System.out.println("---------------------------");
					System.out.printf("Found instance:: ID-%s" + 
									"AMI %s," +
							"type %s," + 
									"state %s"
							+ "and monitoring state %s",
							instance.getInstanceId(),
							instance.getImageId(),
							instance.getInstanceType(),
							instance.getState().getName(),
							instance.getMonitoring().getState());
				}
			}
			describeInstancesRequest.setNextToken(response.getNextToken());
			if(response.getNextToken() == null) {
				done = true;
			}
		}
	}
}