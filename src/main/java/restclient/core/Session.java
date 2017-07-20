package restclient.core;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import restclient.dto.CredentialDTO;
import restclient.serviceinterfaces.UsersInterface;
import tools.Popup;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by bastiangardel on 19.07.17.
 */
public class Session {

    private Map<String,NewCookie> cookies;

    private static Session ourInstance;

    public static Session getInstance() {


        if (ourInstance == null)
            ourInstance = new Session();

        return ourInstance;
    }

    private Session() {
    }

    public Map<String, NewCookie> getCookies() {
        return cookies;
    }

    public Boolean login (String username, String password){

        int status = 0;


        UsersInterface proxy = RestClient.getInstance().getTarget().proxy(UsersInterface.class);

        CredentialDTO credentialDTO = new CredentialDTO();
        credentialDTO.setUsername(username);
        credentialDTO.setPassword(password);

        try{
            Response response = proxy.authenticate(credentialDTO);

            status = response.getStatus();

            System.out.println("HTTP code: " + status);

           cookies = response.getCookies();

            switch (status){
                case 200: break;
                case 401:
                    Popup.showAlert(Alert.AlertType.INFORMATION,
                            "!! Access Deny!!",
                            Integer.toString(status),
                            response.getStatusInfo().getReasonPhrase());
                    break;
                default:
                    Popup.showAlert(Alert.AlertType.ERROR,
                            "!! Authentication Failure!!",
                            Integer.toString(status),
                            response.getStatusInfo().getReasonPhrase());

            }

            response.close();

        } catch (Exception e)
        {

            Platform.runLater(() -> Popup.showErrorAlert("!! Error !!","Authentication Error", e));

        }

        return status == 200;
    }

    public Boolean logout (){

        int status = 0;

        if(cookies != null) {

            RestClient.getInstance().getClient().register(new CookieClientRequestFilter(cookies.get("JSESSIONID")));
            UsersInterface proxy = RestClient.getInstance().getTarget().proxy(UsersInterface.class);

            try{
                Response response = proxy.logout();

                status = response.getStatus();

                System.out.println("HTTP code: " + status);

                switch (status){
                    case 200: break;
                    case 401:
                        Popup.showAlert(Alert.AlertType.INFORMATION,
                                "!! Logout Deny!!",
                                Integer.toString(status),
                                "Already logout");
                        break;
                    default:
                        Popup.showAlert(Alert.AlertType.ERROR,
                                "!! Logout Failure!!",
                                Integer.toString(status),
                                response.getStatusInfo().getReasonPhrase());

                }

                response.close();


                cookies = null;

            } catch (Exception e)
            {


                Popup.showErrorAlert("!! Error !!","Logout Error", e);
            }

        }
        else
        {
            status = 200;
        }


        return status == 200;
    }
}
