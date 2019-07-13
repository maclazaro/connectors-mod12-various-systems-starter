package com.mulesoft.training.beans;

import org.mockftpserver.fake.FakeFtpServer;
import org.mockftpserver.fake.UserAccount;
import org.mockftpserver.fake.filesystem.DirectoryEntry;
import org.mockftpserver.fake.filesystem.FileEntry;
import org.mockftpserver.fake.filesystem.FileSystem;
import org.mockftpserver.fake.filesystem.UnixFakeFileSystem;

//mockftpserver uses slf4j API to log out messages to console. slf4j is automatically configured to use log4j2 logging methods.
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

public class FtpInitBean implements InitializingBean{
	
	@Value("${partner2.ftp.port}") Integer ftpPort;
	@Value("${partner2.ftp.username}") String username;
	@Value("${partner2.ftp.password}") String password;
	
	private static Logger logger = LoggerFactory.getLogger(FtpInitBean.class);
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		logger.info("Creating FTP server...");
		
		FakeFtpServer fakeFtpServer = new FakeFtpServer();
        fakeFtpServer.setServerControlPort(ftpPort);

        fakeFtpServer.addUserAccount(new UserAccount(username, password, "/data"));
        FileSystem fileSystem = new UnixFakeFileSystem();
        fileSystem.add(new DirectoryEntry("/data"));
        fileSystem.add(new DirectoryEntry("/data.test"));
        fileSystem.add(new DirectoryEntry("/data/test/processed"));
        fileSystem.add(new FileEntry("/data/test/testfile.csv", "header1,header2\n1234567890,92838448\n2829444,92849482"));
        fileSystem.add(new FileEntry("/data/test/testfile2.csv", "header1,header2\n983924,92838448\n2829444,92849482"));
        fakeFtpServer.setFileSystem(fileSystem);

        fakeFtpServer.start();
		
		logger.info("FTP server created with credentials: \n\nuser = " + username + "\npassword = " + password + "\n");
		
	}

}
