package nullable.software.core;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class SNS {
    private String accessKey;
    private String secretKey;
    private String snsARN;

    public SNS(String accessKey, String secretKey, String snsARN) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.snsARN = snsARN;
    }

    public void send(String message) {
        BasicAWSCredentials creds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonSNS client = AmazonSNSClientBuilder.standard().withRegion(Regions.fromName("us-west-2"))
                .withCredentials(new AWSStaticCredentialsProvider(creds)).build();
        PublishRequest publishRequest = new PublishRequest(snsARN, message);
        PublishResult publishResult = client.publish(publishRequest);
    }
}
