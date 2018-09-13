package dao;

import java.net.Socket;

import constants.Dml;
import dto.OrderDto;

public class OrderDao {
	public void execute(int number, OrderDto dto, Socket sock) {
		switch (number) {
		case Dml.INSERT:
			break;
		case Dml.DELETE:
			break;
		case Dml.UPDATE:
			break;
		case Dml.SELECT:
			break;
		}

	}
}
