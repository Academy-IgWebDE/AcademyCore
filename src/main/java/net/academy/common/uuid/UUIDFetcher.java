package net.academy.common.uuid;

import net.academy.common.anotiation.comentState.ComentState;
import net.academy.common.anotiation.comentState.State;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 */
@ComentState(commentState = State.NONE)
public class UUIDFetcher {


    public UUIDFetcher() {

    }

    public String getNameFromUUID(UUID uuid) {
        return Bukkit.getOfflinePlayer(uuid).getName();
    }

    private static final String UUID_URL = "https://api.mojang.com/users"
            + "/profiles/minecraft/";

    private static final Pattern UUID_PATTERN = Pattern.compile("\"id\"\\s*:\\s*\"(.*?)\"");

    /**
     * get the UUID from a Player, even if there are no online
     * @param name the name of the Player
     * @return the UUID from the player
     */
    public UUID getUUID(String name) {
        String output = callURL(UUID_URL + name);
        Matcher m = UUID_PATTERN.matcher(output);
        if (m.find()) {
            return UUID.fromString(insertDashes(m.group(1)));
        }
        return null;
    }

    /**
     * complete the UUID for Spigot, because Mojang do not save '-'
     * @param uuid gives the UUID as a string without '-'
     * @return returns the UUID with '-' as string
     */
    private String insertDashes(String uuid) {
        StringBuilder sb = new StringBuilder(uuid);
        sb.insert(8, '-');
        sb.insert(13, '-');
        sb.insert(18, '-');
        sb.insert(23, '-');
        return sb.toString();
    }

    /**
     * get the UUID from the urlStr
     * @param player the players name to get the Mojang information of
     * @return the complete String from Mojang
     */
    private String callURL(String player) {
        String urlStr = UUID_URL + player;
        StringBuilder stringBuilder = new StringBuilder();
        URLConnection connection;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        try {
            connection = new URL(urlStr).openConnection();
            if (connection != null) {
                connection.setReadTimeout(60 * 1000);
            }
            if (connection != null && connection.getInputStream() != null) {
                inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
                bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    stringBuilder.append(line).append("\n");
                    line = bufferedReader.readLine();
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Throwable ignored) {
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (Throwable ignored) {
                }
            }
        }
        return stringBuilder.toString();
    }

}
