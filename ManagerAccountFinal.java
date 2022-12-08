package Final;

import BaiTapInterFace.Bai5.Account;

import java.util.ArrayList;

public class ManagerAccountFinal implements AccountManagerFinal {
    private ArrayList<AccountFinal> accountFinals;

    public ManagerAccountFinal() {
        accountFinals = new ArrayList<>();
    }

    public ManagerAccountFinal(ArrayList<AccountFinal> accountFinals) {

        this.accountFinals = accountFinals;
    }

    public ArrayList<AccountFinal> getAccountFinals() {
        return accountFinals;
    }

    public void setAccountFinals(ArrayList<AccountFinal> accountFinals) {
        this.accountFinals = accountFinals;
    }

    @Override
    public boolean signup(AccountFinal accountFinal) {
        if(!check(accountFinal)) {
            accountFinals.add(accountFinal);
            return true;
        }
        return false;
    }

    private boolean check(AccountFinal accountFinal) {

        for(int i = 0; i < accountFinals.size(); i++) {
            if (accountFinal.equals(accountFinals.get(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean login(AccountFinal accountFinal) {
        for(int i = 0; i < accountFinals.size(); i++) {
            if(accountFinal.equals(accountFinals.get(i)) &&
                    accountFinal.getPassWord().equals(accountFinals.get(i).getPassWord()) &&
                    accountFinals.get(i) instanceof Activity) {
                var acc = (Activity)accountFinals.get(i);
                acc.setOnline(true);
                return true;

            }
        }
        return false;
    }

    @Override
    public boolean logout(AccountFinal accountFinal) {
        for(int i = 0; i < accountFinals.size(); i++) {
            if(accountFinal.equals(accountFinals.get(i)) &&
                    accountFinal.getPassWord().equals(accountFinals.get(i).getPassWord()) &&
                    accountFinals.get(i) instanceof Activity) {
                var acc = (Activity)accountFinals.get(i);
                acc.setOnline(false);
                return true;

            }
        }
        return false;
    }

    @Override
    public boolean changePassWord(AccountFinal account) {
        for(int i = 0; i < accountFinals.size(); i++) {
            if(accountFinals.get(i).equals(account)) {
                accountFinals.get(i).setPassWord(account.getPassWord());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean lockAccount(AccountFinal accountFinal) {
        for(int i = 0; i < accountFinals.size(); i++) {
            if(accountFinal.equals(accountFinals.get(i)) &&
                accountFinal.getPassWord().equals(accountFinals.get(i).getPassWord())
                && accountFinals.get(i) instanceof Activity) {
                var acc = (Activity)accountFinals.get(i);
                acc.setActivity(false);
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean openAccount(AccountFinal accountFinal) {
        for(int i = 0; i < accountFinals.size(); i++) {
            if(accountFinal.equals(accountFinals.get(i)) &&
                    accountFinal.getPassWord().equals(accountFinals.get(i).getPassWord())
                    && accountFinals.get(i) instanceof Activity) {
                var acc = (Activity)accountFinals.get(i);
                acc.setActivity(true);
                return true;
            }
        }
        return false;

    }

    @Override
    public void showlistAccount() {
        System.out.printf("%-15s%-15s\n","User", "PassWord");
        for (var item :
                accountFinals) {
            showList(item);
        }
    }

    private void showList(AccountFinal item) {
        System.out.printf("%-15s%-15s\n",item.getUser(), item.getPassWord());
    }
}
