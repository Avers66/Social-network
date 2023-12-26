package avers66.microservice.authorization.impl.repository;

import org.springframework.stereotype.Repository;
import avers66.library.core.repository.BaseRepository;
import avers66.microservice.authorization.domain.model.Captcha;

/**
 * CaptchaRepository
 *
 * @author Mikhail Chechetkin
 */

@Repository
public interface CaptchaRepository extends BaseRepository<Captcha> {
    Captcha getBySecret(String secret);
}
