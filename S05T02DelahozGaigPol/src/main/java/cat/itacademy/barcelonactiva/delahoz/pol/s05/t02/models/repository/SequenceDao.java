package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.repository;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.SequenceException;

public interface SequenceDao {
	
	long getNextSequenceId(String key) throws SequenceException;
	
}