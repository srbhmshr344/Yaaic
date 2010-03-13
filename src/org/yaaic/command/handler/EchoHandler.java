/*
Yaaic - Yet Another Android IRC Client

Copyright 2009-2010 Sebastian Kaspari

This file is part of Yaaic.

Yaaic is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Yaaic is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Yaaic.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.yaaic.command.handler;

import org.yaaic.command.BaseHandler;
import org.yaaic.command.CommandException;
import org.yaaic.irc.IRCService;
import org.yaaic.model.Broadcast;
import org.yaaic.model.Conversation;
import org.yaaic.model.Message;
import org.yaaic.model.Server;

import android.content.Intent;

/**
 * Command: /echo <text>
 * 
 * @author Sebastian Kaspari <sebastian@yaaic.org>
 */
public class EchoHandler extends BaseHandler
{
	/**
	 * Execute /echo
	 */
	@Override
	public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException 
	{
		if (params.length > 1) {
			Message message = new Message(BaseHandler.mergeParams(params));
			conversation.addMessage(message);
			
			Intent intent = new Intent(Broadcast.CONVERSATION_MESSAGE);
			intent.putExtra(Broadcast.EXTRA_SERVER, server.getId());
			intent.putExtra(Broadcast.EXTRA_CONVERSATION, conversation.getName());
			service.sendBroadcast(intent);
		} else {
			throw new CommandException("Text is missing");
		}
	}
	
	/**
	 * Usage of /echo
	 */
	@Override
	public String getUsage()
	{
		return "/echo <text>";
	}
}
