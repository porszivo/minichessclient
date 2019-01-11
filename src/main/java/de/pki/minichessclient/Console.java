package de.pki.minichessclient;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import de.pki.minichessclient.connector.Client;
import de.pki.minichessclient.connector.IMCSGame;

/**
 * This class provides an old-fashion text-based console for the minichess game. This console takes commands from the
 * command line and sends them to the telnet server via the <code>connector.Client</code>. Furthermore it formats the
 * responses to be readable in the command line.
 *
 */
public class Console {

  // The Client that sends the commands to the telnet server.
  protected Client client;
  // Reads input from the command line.
  protected Scanner scanner = new Scanner(System.in);

  final protected String host = Messages.getString("Console.host"); //$NON-NLS-1$
  final protected int port = Integer.parseInt(Messages.getString("Console.port")); //$NON-NLS-1$

  /**
   * Initializes the connection to the telnet server.
   * 
   * @throws IOException On any network errors.
   */
  public Console() throws IOException {
    System.out.println("================\n" //
        + "=   Minichess  =\n" //
        + "================\n");
    System.out.println("Initializing connection to telnet server " + host + ":" + port);
    this.connect(host, port);
    System.out.println("Connection established.");
  }

  /**
   * Reads a command from the command line, interprets it and sends it to the telnet server.
   * Also formats and outputs the response.
   * 
   * @return true if further commands shall be requested, false on exit.
   * @throws IOException On any network errors.
   */
  public boolean requestCommand() throws IOException {
    System.out.print("> ");
    String in = scanner.nextLine();
    String[] parts = in.split(" ");
    String command = parts[0];
    boolean goOn = true;
    String response;

    if (command.trim().toLowerCase().equals(Command.ACCEPT.toString().toLowerCase())) {
      if (parts.length == 2) {
        response = this.accept(parts[1]);
      } else if (parts.length == 3) {
        response = this.accept(parts[1], parts[2]);
      } else {
        response = "Wrong number of arguments!";
      }
    } else if (command.trim().toLowerCase().equals(Command.LIST.toString().toLowerCase())) {
      response = this.getGameList();
    } else if (command.trim().toLowerCase().equals(Command.HELP.toString().toLowerCase())) {
      response = this.listMethods();
    } else if (command.trim().toLowerCase().equals(Command.LOGIN.toString().toLowerCase())) {
      if (parts.length == 3) {
        response = this.login(parts[1], parts[2]);
      } else {
        response = "Wrong number of arguments!";
      }
    } else if (command.trim().toLowerCase().equals(Command.REGISTER.toString().toLowerCase())) {
      if (parts.length == 3) {
        response = this.register(parts[1], parts[2]);
      } else {
        response = "Wrong number of arguments!";
      }
    } else if (command.trim().toLowerCase().equals(Command.OFFER.toString().toLowerCase())) {
      if (parts.length == 1) {
        response = this.offerGameAndWait();
      } else if (parts.length == 2) {
        response = this.offerGameAndWait(parts[1]);
      } else {
        response = "Wrong number of arguments!";
      }
    } else if (command.trim().toLowerCase().equals(Command.PASSWORD.toString().toLowerCase())) {
      if (parts.length == 1) {
        response = this.changePassword(parts[1]);
      } else {
        response = "Wrong number of arguments!";
      }
    } else if (command.trim().toLowerCase().equals(Command.RATINGS.toString().toLowerCase())) {
      response = this.getRatingList();
    } else if (command.trim().toLowerCase().equals(Command.RERATE.toString().toLowerCase())) {
      response = this.rerate();
    } else if (command.trim().toLowerCase().equals(Command.EXIT.toString().toLowerCase())) {
      response = this.exit();
      goOn = false;

    } else {
      response = "Command not recognized. Please try again.";

    }
    System.out.println(response);
    return goOn;

  }

  protected String listMethods() {
    StringBuilder builder = new StringBuilder();
    String header = "=================\n" //
        + "=   Operations  =\n" //
        + "=================\n";
    builder.append(header);

    for (Command command : Command.values()) {
      builder.append(command.toString().toLowerCase() + "\n"); //$NON-NLS-1$
    }

    return builder.toString();

  }
  
  /**
   * Proxy methods for accessing the <code>Client</code>. 
   * Used to handle and format the response and to add some meaningful text around it.
   * 
   */

  protected String accept(String gameId) throws IOException {
    char response = this.client.accept(gameId);
    return "Game started. You are " + response;
  }

  protected String accept(String gameId, String color) throws IOException {
    this.client.accept(gameId, color.charAt(0));
    return "Game started. You are " + color.charAt(0);
  }

  protected String changePassword(String password) throws IOException {
    this.client.changePassword(password);
    return "Password successfully changed.";
  }

  protected String getGameList() throws IOException {
    List<IMCSGame> games = this.client.getGameList();
    StringBuilder builder = new StringBuilder();
    builder.append("Games available: \n");
    for (IMCSGame game : games) {
      builder.append(game.toString() + "\n");
    }
    return builder.toString();

  }

  protected String getRatingList() throws IOException {
    Map<String, Integer> ratings = this.client.getRatingsList();
    StringBuilder builder = new StringBuilder();
    builder.append("Ratings: \n");
    for (String player : ratings.keySet()) {
      builder.append(player + ": " + ratings.get(player) + "\n");
    }
    return builder.toString();
  }

  protected String login(String username, String password) throws IOException, RuntimeException {
    this.client.login(username, password);
    return "Successfully logged in.";
  }

  protected String register(String username, String password) throws IOException, RuntimeException {
    this.client.register(username, password);
    return "Successfully registered.";
  }

  protected String offerGameAndWait() throws IOException, RuntimeException {
    char color = this.client.offerGameAndWait();
    return "Game started. You play " + color;
  }

  protected String offerGameAndWait(String color) throws IOException, RuntimeException {
    this.client.offerGameAndWait(color.charAt(0));
    return "Game started. You play " + color.charAt(0);
  }

  protected String rerate() throws IOException {
    this.client.rerate();
    return "Rerated.";

  }

  protected String exit() throws IOException {
    this.client.close();
    return "Bye bye!";
  }

  protected void connect(String host, int port) throws IOException {
    this.client = new Client(host, port);
  }

}
