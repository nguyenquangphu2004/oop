package Final;

import java.util.ArrayList;

public interface AccountManagerFinal {

    boolean signup(AccountFinal accountFinal);
    boolean login(AccountFinal accountFinal);
    boolean logout(AccountFinal accountFinal);

    boolean changePassWord(AccountFinal accountFinal);
    boolean lockAccount(AccountFinal accountFinal);
    boolean openAccount(AccountFinal accountFinal);

    void showlistAccount();


}
