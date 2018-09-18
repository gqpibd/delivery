package dao;

import java.net.Socket;

import constants.Dml;
import dto.ReviewDto;

public class ReviewDao {
	public void execute(int number, ReviewDto dto, Socket sock) {
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
