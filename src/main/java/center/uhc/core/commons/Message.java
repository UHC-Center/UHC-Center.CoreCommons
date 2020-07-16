package center.uhc.core.commons;

import de.dytanic.cloudnet.common.document.gson.JsonDocument;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * UHC Center - Core
 * Message Utility
 *
 * Uses for message formatting.
 *
 * @author  Gabriella Hotten
 * @version 1.0
 * @since   2020-06-06
 */
public class Message {

    public static String formatSystem(ChatColor primary, String head, String body) { return "" + primary + head + " §8» " + ChatColor.RESET + primary + body; }
    public static void broadcast(String message) { Bukkit.getServer().broadcastMessage(message); }
    public static void console(String message) { Bukkit.getServer().getConsoleSender().sendMessage(message); }
    public static void commandConsole(String command) { Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command); }
    public static String combine(String[] args, int start) {
        String msg = "";
        for (int i = start; i<args.length; i++) {
            if (msg.equals(""))
                msg = msg + args[i];
            else
                msg = msg + " " + args[i];
        }
        return msg;
    }

    public static String addLinebreaks(String input, int maxLineLength) {
        StringTokenizer tok = new StringTokenizer(input, " ");
        StringBuilder output = new StringBuilder(input.length());
        int lineLen = 0;
        while (tok.hasMoreTokens()) {
            String word = tok.nextToken();

            if (lineLen + word.length() > maxLineLength) {
                output.append("\n");
                lineLen = 0;
            }
            output.append(word);
            lineLen += word.length();
        }
        return output.toString();
    }

    public static ArrayList<String> addLinebreaks(String input, int maxLineLength, String toAppendAfterNewline) {
        ArrayList<String> result = new ArrayList<>();

        StringTokenizer tok = new StringTokenizer(input, " ");
        StringBuilder output = new StringBuilder();
        output.insert(0, toAppendAfterNewline);
        int lineLen = 0;
        while (tok.hasMoreTokens()) {
            String word = tok.nextToken();

            if (lineLen + word.length() > maxLineLength) {
                result.add(output.toString());
                output = new StringBuilder();
                output.append(toAppendAfterNewline);
                lineLen = 0;
            }
            output.append(word).append(" ");
            lineLen += word.length();
        }
        result.add(output.toString());

        return result;
    }

    public static void sendToNetwork(String message, String permission) {
        CloudNetDriver.getInstance().getMessenger().sendChannelMessage("uc", "network_message_util", new JsonDocument()
        .append("message", message)
        .append("permission", (permission == null ? "" : permission)));
    }

}
