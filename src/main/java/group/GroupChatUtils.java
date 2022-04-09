package group;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import util.Chat;
import util.ConfigHandler;
import util.Lang;
import util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupChatUtils {
    private final GroupChat plugin;

    public GroupChatUtils(GroupChat plugin) {
        this.plugin = plugin;
    }

    public void fail(Player player, String why) {
        player.sendMessage(Chat.RED + "Error: " + Chat.WHITE + why);
    }

    public void message(Player player, String msg) {
        player.sendMessage(Lang.GROUP_TAG + msg);
    }

    public boolean isValidName(String theName) {
        if (theName.length() < 4 || theName.length() > 16) return false;
        return !Utils.chatInArray(theName, new String[]{"getThisFromConfig"});
    }

    public List<String> getGroupNames() {
        List<String> results = new ArrayList<>();
        plugin.getGroups().forEach(group -> results.add(group.name()));
        return results;
    }

    public Group getGroupByName(String name) {
        Group theGroup = null;
        for (Group group : plugin.getGroups()) {
            if (group.name().equalsIgnoreCase(name)) {
                theGroup = group;
            }
        }
        return theGroup;
    }

    public Group getPlayerGroup(UUID uuid) {
        Group theGroup = null;
        for (Group group : plugin.getGroups()) {
            if (group.getMembers().contains(uuid)) {
                theGroup = group;
            }
        }
        return theGroup;
    }

    public boolean playerInGroup(UUID uuid) {
        Group theGroup = null;
        for (Group group : plugin.getGroups()) {
            if (group.getMembers().contains(uuid)) {
                theGroup = group;
            }
        }
        return theGroup != null;
    }

    public boolean groupChatEnabled(Player p) {
        return plugin.getConfig().getBoolean("groupChat." + p.getUniqueId());
    }

    public void displayMenu(CommandSender p) {
        String bar = Chat.GRAY + Chat.bar(30) + Chat.RESET;
        String arrow = Chat.DARK_GRAY + "> " + Chat.AQUA;
        String desc = Chat.WHITE + "- ";
        String command = Chat.WHITE + "";
        String arg = Chat.GRAY + "";
        p.sendMessage(bar + Chat.AQUA + " [Group Help] " + bar);
        p.sendMessage(arrow + "/group " + command + "create" + arg + " <name> " + desc + "Create a group for "
                + ConfigHandler.COST_STRING + " points");
        p.sendMessage(arrow + "/group " + command + "leave " + arg + "<player> " + desc + "Leave your current group");
        p.sendMessage(arrow + "/group " + command + "invite " + arg + "<player> " + desc
                + "Invite a player to join your group");
        p.sendMessage(
                arrow + "/group " + command + "kick " + arg + "<player> " + desc + "Remove a player from your group");
        p.sendMessage(arrow + "/group " + command + "disband " + arg + "<name> " + desc + "Disband your group");
        p.sendMessage(
                arrow + "/group " + command + "promote " + arg + "<player> " + desc + "Promote a member to captain");
        p.sendMessage(
                arrow + "/group " + command + "togglechat " + desc + "Toggle default chat between group and public");
        p.sendMessage(arrow + "/g " + command + arg + "<message> " + desc + "Send a message to your group");
    }

}
