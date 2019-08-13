


fun main(args: Array<String>) {
    val menuList: ArrayList<Menu> = arrayListOf()
    menuList.add(Menu.MenuItem("mayo", "15")) //adding a menu item

    //adding a menu group with single menu item
    val menuGroupList: ArrayList<Menu> = arrayListOf()
    menuGroupList.add(Menu.MenuItem("shwarma", "56"))
    menuList.add(Menu.MenuGroup(menuGroupList, null))

    //adding a menu group inside a menu group with a single menu item
    val menuGroupList1: ArrayList<Menu> = arrayListOf()
    val menuGroupList2: ArrayList<Menu> = arrayListOf()
    menuGroupList2.add(Menu.MenuItem("grill", "55"))
    menuGroupList1.add(Menu.MenuGroup(menuGroupList2, null))

    menuList.add(Menu.MenuGroup(menuGroupList1,null))

    print(""+getItemPrice("grill",RestaurantMenu(menuList,"80")))
}


fun getItemPrice(item: String, restaurantMenu: RestaurantMenu): String? {
    var itemPrice: String? = ""
    restaurantMenu.menuList.forEach {
        if(it is Menu.MenuItem) {
            if(it.itemName == item && itemPrice == "") {
                itemPrice = it.itemPrice
            }
        } else if(it is Menu.MenuGroup) {
            if(itemPrice == "") {
                itemPrice = getItemPriceFromMenuGroup(item, it, itemPrice)
            }
        }
    }
    if(itemPrice == null) {
        itemPrice = restaurantMenu.itemPrice
    }
    return itemPrice
}


fun getItemPriceFromMenuGroup(item: String, it: Menu.MenuGroup, itemPrice: String?): String? {
    var itemPrice1 = itemPrice
    it.menu.forEach {
        if(it is Menu.MenuItem) {
            if(it.itemName == item) {
                itemPrice1 = it.itemPrice
            }
        } else if(it is Menu.MenuGroup) {
            itemPrice1 = getItemPriceFromMenuGroup(item, it, itemPrice1)
            if(itemPrice1 == null) {
                itemPrice1 = it.itemPrice
            }
        }
    }
    if (itemPrice1 == null) {
        itemPrice1 = it.itemPrice
    }
    return itemPrice1
}


data class RestaurantMenu(val menuList: ArrayList<Menu>, val itemPrice: String)

sealed class Menu {
    data class MenuItem(val itemName: String, val itemPrice: String?) : Menu()
    data class MenuGroup(val menu: ArrayList<Menu>, val itemPrice: String?) : Menu()
}