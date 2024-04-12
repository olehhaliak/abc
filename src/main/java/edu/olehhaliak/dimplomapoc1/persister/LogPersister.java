package edu.olehhaliak.dimplomapoc1.persister;

public interface LogPersister {
   Log persistLog(Log log);

   Log readLog(String offset);
}
