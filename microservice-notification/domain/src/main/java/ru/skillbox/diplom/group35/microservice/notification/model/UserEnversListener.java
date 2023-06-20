package ru.skillbox.diplom.group35.microservice.notification.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.RevisionListener;
import ru.skillbox.diplom.group35.library.core.utils.SecurityUtil;

/**
 * UserEnversListener
 *
 * @Author Tretyakov Alexandr
 */


@Slf4j
@RequiredArgsConstructor
public class UserEnversListener implements RevisionListener {
    private final SecurityUtil securityUtil;

    @Override
    public void newRevision(Object revisionEntity) {
        UserRevision userRevision = (UserRevision) revisionEntity;
        UserAuthor userAuthor = new UserAuthor();
        try {
            userAuthor.setUserId(securityUtil.getAccountDetails().getId());
            userAuthor.setEmail(securityUtil.getAccountDetails().getEmail());
        } catch (NullPointerException ex) {
            userAuthor.setUserId(null);
            userAuthor.setEmail("accountService@registration");
        } finally { userRevision.setUserAuthor(userAuthor);}
    }
}
