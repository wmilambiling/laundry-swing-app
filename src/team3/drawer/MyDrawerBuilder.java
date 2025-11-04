package team3.drawer;

import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.MenuValidation;
import raven.drawer.component.menu.SimpleMenuOption;
import team3.form.TestForm;
import team3.main.Main;
import team3.tabbed.WindowsTabbed;


public class MyDrawerBuilder extends SimpleDrawerBuilder {

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        return new SimpleHeaderData()
                //jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/meow_peek.jpg"))); // NOI18N
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/team3/image/logo_resized.png")))
                // .setIcon(new AvatarIcon(getClass().getResource("/team3/image/logo.png"), 60, 60, 999))
                .setTitle("Bubble Buddy")
                .setDescription("team3@gmail.com");
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {
        String menus[][] = {
            {"~OVERVIEW~"},
            {"Dashboard"},
            {"Payments"},
            {"Activities"},
            {"Orders"},
            {"~HISTORY~"},
            {"Clients"},
            {"Sales"},
            {"Logout"}};

        String icons[] = {
            "dashboard.svg",
            "payment.svg",
            "activity.svg",
            "receive.svg",
            "client.svg",
            "sales.svg",
            "logout.svg"};

        return new SimpleMenuOption()
                .setMenus(menus)
                .setIcons(icons)
                .setBaseIconPath("team3/drawer/icon")
                // .setIconScale(0.45f)
                .setIconScale(.60f)
                .addMenuEvent(new MenuEvent() {
                    @Override
                    public void selected(MenuAction action, int index, int subIndex) {
                        if (index == 0) {
                            WindowsTabbed.getInstance().addTab("Form Added", new TestForm());
                        } else if (index == 9) {
                            Main.main.login();
                        }
                        System.out.println("Menu selected " + index + " " + subIndex);
                    }
                })
                .setMenuValidation(new MenuValidation() {
                    @Override
                    public boolean menuValidation(int index, int subIndex) {
//                        if(index==0){
//                            return false;
//                        }else if(index==3){
//                            return false;
//                        }
                        return true;
                    }

                });
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData()
                .setTitle("Team Laundry App")
                .setDescription("Version 0.1.0");
    }

    @Override
    public int getDrawerWidth() {
        return 275;
    }
}
