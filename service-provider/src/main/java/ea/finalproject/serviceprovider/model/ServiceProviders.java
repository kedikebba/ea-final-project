package ea.finalproject.serviceprovider.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceProviders {

    private List<ServiceProvider> serviceProviders;
    private List<Plan> plans;

    public ServiceProviders() {
        serviceProviders = new ArrayList<>(
                Arrays.asList(
                        new ServiceProvider("ATNT", "atnt", "USA",
                                Arrays.asList(
                                        new Plan("antn100", "online offer", 25.00, "monthly", "prepaid"),
                                        new Plan("antn101", "limited time offer", 45.00, "monthly", "prepaid data"),
                                        new Plan("antn102", "AT&T UNLIMITED & MORE PREMIUM", 48.00, "monthly", "premium"),
                                        new Plan("antn103", "AT&T UNLIMITED EXTRA", 40.00, "monthly", "extra pack"),
                                        new Plan("antn104", "AT&T UNLIMITED STARTER", 35.00, "monthly", "starter pack")
                                )
                        ),
                        new ServiceProvider("Verizon", "verizon", "USA",
                                Arrays.asList(
                                        new Plan("verizon100", "START UNLIMITED", 35.00, "monthly", "prepaid"),
                                        new Plan("verizon101", "PLAY MORE UNLIMITED", 45.00, "monthly", "prepaid"),
                                        new Plan("verizon102", "DO MORE UNLIMITED", 50.00, "monthly", "prepaid"),
                                        new Plan("verizon103", "GET MORE UNLIMITED", 55.00, "monthly", "prepaid")
                                )),
                        new ServiceProvider("MTN", "mtnng", "Nigeria",
                                Arrays.asList(
                                        new Plan("mtnng100", "10 Minutes Pack", 100.00, "daily", "prepaid data"),
                                        new Plan("mtnng101", "20 Minutes Pack", 200.00, "daily", "prepaid data"),
                                        new Plan("mtnng102", "50 Minutes Pack", 500.00, "daily", "prepaid data"),
                                        new Plan("mtnng103", "100 Minutes Pack", 1000.00, "daily", "prepaid data")
                                )),
                        new ServiceProvider("GLO", "glong", "Nigeria",
                                Arrays.asList(
                                        new Plan("glong100", "Glo 11k/s Prepaid Plan", 100.00, "daily", "prepaid data"),
                                        new Plan("glong101", "Glo Jollific8", 500.00, "daily", "prepaid data"),
                                        new Plan("glong102", "Infinito", 1000.00, "monthy", "prepaid data"),
                                        new Plan("glong103", "G-BAM", 200.00, "daily", "prepaid data")
                                )),
                        new ServiceProvider("Etisalat", "etisalatng", "Nigeria",
                                Arrays.asList(

                                )),
                        new ServiceProvider("NTC", "ntcnp", "Nepal",
                                Arrays.asList(

                                )),
                        new ServiceProvider("NCEL", "ncel", "Nepal",
                                Arrays.asList(

                                )),
                        new ServiceProvider("MTN", "mtnug", "Uganda",
                                Arrays.asList(

                                )),
                        new ServiceProvider("AIRTEL", "airtelug", "Uganda",
                                Arrays.asList(

                                ))
                )
        );
    }

    public List<ServiceProvider> getServiceProviders() {
        return serviceProviders;
    }
}
/*new ServiceProvider("ATNT","antn","USA", Arrays.asList(new Plan( "A100","online offer", 25.00, "monthly", "prepaid")),
        new ServiceProvider("Verizon","verizon","USA", Arrays.asList())*/
                       /* 3new ServiceProvider("MTN","mtnng","Nigeria",Arrays.asList()),
                        4new ServiceProvider("GLO","glong","Nigeria",Arrays.asList()),
                        5new ServiceProvider("Etisalat","etisalatng","Nigeria",Arrays.asList()),
                        6new ServiceProvider("NTC","ntcnp","Nepal",Arrays.asList()),
                        7new ServiceProvider("NTC","ntcnp","Nepal",Arrays.asList()),
                        8new ServiceProvider("MTN","mtnug","Uganda",Arrays.asList()),
                        new ServiceProvider("AIRTEL","airtelug","Uganda",Arrays.asList())*/