package com.github.nderwin.lee8;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    private static final String ALLOWED_METHODS = Arrays.deepToString(new String[]{
        HttpMethod.DELETE,
        HttpMethod.GET,
        HttpMethod.HEAD,
        HttpMethod.OPTIONS,
        HttpMethod.PATCH,
        HttpMethod.POST,
        HttpMethod.PUT
    }).replace("[", "").replace("]", "");

    private final static String[] DEFAULT_ALLOWED_HEADERS = new String[]{
        HttpHeaders.IF_MATCH,
        "origin"
    };

    private final static String[] DEFAULT_EXPOSED_HEADERS = new String[]{
        HttpHeaders.LOCATION,
        HttpHeaders.ETAG
    };

    private final static int MAX_AGE = 42 * 60 * 60;

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) throws IOException {
        final MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Headers", appendHeaders(headers.get("Access-Control-Allow-Headers"), DEFAULT_ALLOWED_HEADERS));
        headers.add("Access-Control-Expose-Headers", appendHeaders(headers.get("Access-Control-Expose-Headers"), DEFAULT_EXPOSED_HEADERS));
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
        headers.add("Access-Control-Max-Age", "" + MAX_AGE);
    }

    private String appendHeaders(final List<Object> existingHeaders, final String[] additionalHeaders) {
        final StringBuilder sb = new StringBuilder("");

        if (null != existingHeaders) {
            existingHeaders.forEach((s) -> {
                sb.append(s).append(", ");
            });
        }

        if (null != additionalHeaders) {
            for (String s : additionalHeaders) {
                sb.append(s).append(", ");
            }
        }

        if (0 < sb.length()) {
            sb.delete(sb.length() - 2, sb.length());
        }

        return sb.toString();
    }
}
