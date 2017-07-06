package be.hapa.domain;

import org.springframework.data.rest.core.config.Projection;

/**
 * Created by hpacquee on 05/07/2017.
 */
@Projection(name = "no_uuid", types= Department.class)
public interface NoUUIDProjection {
    String getName();
}
