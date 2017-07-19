package org.practice.springjersey.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Component
public class ApiLinkBuilder {

    public static final String LOCALHOST = "localhost";
    public static final int REMOVE_PORT = -1;
    public static final String COLON = ":";

    public URI createObjectLink(UriInfo uriInfo, String code){
        if(isUsingIpForDomain(uriInfo)){
            return uriInfo.getAbsolutePathBuilder().host(LOCALHOST)
                    .port(getPortNumberFromURI(uriInfo)).path(code).build();
        }
        return uriInfo.getAbsolutePathBuilder().path(code).build();
    }

    private boolean isUsingIpForDomain(UriInfo uriInfo){
        return StringUtils.contains(uriInfo.getAbsolutePath().getAuthority(),COLON);
    }

    private int getPortNumberFromURI(UriInfo uriInfo){
        String port = StringUtils.substringAfter(uriInfo.getAbsolutePath().getAuthority(), COLON);
        if(port != null && LOCALHOST.equalsIgnoreCase(getDomainFromURI(uriInfo))){
            try{
                return Integer.parseInt(port);
            }catch(NumberFormatException e){
                return REMOVE_PORT;
            }
        }
        else
            return REMOVE_PORT;
    }

    private String getDomainFromURI(UriInfo uriInfo){
        return StringUtils.substringBefore(uriInfo.getAbsolutePath().getAuthority(),COLON);
    }

}
