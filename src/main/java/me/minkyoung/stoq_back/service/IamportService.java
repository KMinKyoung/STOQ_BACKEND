package me.minkyoung.stoq_back.service;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class IamportService {

    private final IamportClient iamportClient;

    public Payment verifyPayment(String impUid) throws Exception {
        IamportResponse<Payment> response = iamportClient.paymentByImpUid(impUid);
        return response.getResponse();
    }
}
