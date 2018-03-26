package cc.openhome.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class UserService {
	private String USERS;
	
	public UserService(String USERS) {
		this.USERS = USERS; //�����û�Ŀ¼
	}
	
	public String getUsers() {
		return USERS;
	}
	
	//����û����ϼ��Ƿ񴴽���ȷ���û��Ƿ�ע��
	public boolean isInvalidUsername(String username) {
		// TODO Auto-generated method stub
		for(String file : new File(USERS).list()) {
			if (file.equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isInvalidPassword(String password, String confirmedpasswd) {
		// TODO Auto-generated method stub
		return password == null ||
				password.length() < 6 ||
				password.length() > 16 ||
				!password.equals(confirmedpasswd);
	}
	
	public boolean isInvalidEmail(String email) {
		// TODO Auto-generated method stub
		return email == null ||
				!email.matches("^[_a-z0-9-]+([.]" + "[_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
	}
	
	//�����û����ϳأ���profile�д����ʼ�������
	public void createUserData(String email, String username, String password)  
			throws IOException{
		// TODO Auto-generated method stub
		File userhome = new File(USERS + "/" + username);
		userhome.mkdirs();
		BufferedWriter writer = new BufferedWriter(new FileWriter(userhome + "/profile"));
		writer.write(email + "\t" + password);
		writer.close();
	}
	
	//����½�û�����������
	public boolean checkLogin(String username, String password) 
			throws IOException {
		// TODO Auto-generated method stub
		if (username != null && password != null) {
			// ��ȡ�û����ϼ��е�profile�ļ���
			for (String file : new File(USERS).list()) {
				if (file.equals(username)) {
					BufferedReader reader = new BufferedReader(
							new FileReader(USERS + "/" + file + "/profile"));
					String passwd = reader.readLine().split("\t")[1];
					if (passwd.equals(password)) {
						reader.close();
						return true;
					}
					reader.close();
				}
			}
		}
		return false;
	}
	
	// ����.txt�ļ���
	private class TxtFilenameFilter implements FilenameFilter {
		@Override
		public boolean accept(File dir, String name) {
			return name.endsWith(".txt");
		}
	}

	private TxtFilenameFilter filenameFilter = new TxtFilenameFilter();

	// TreeMap�����ã���Ϊϣ����Ϣ������Խ����Խ����ͷ��ʾ
	private class DateComparator implements Comparator<Date> {
		@Override
		public int compare(Date d1, Date d2) {
			return -d1.compareTo(d2);
		}
	}
	
	private DateComparator comparator = new DateComparator();

	public Map<Date, String> readMessage(String username) throws IOException {
		File border = new File(USERS + "/" + username);
		String[] txts = border.list(filenameFilter);

		Map<Date, String> messages = new TreeMap<Date, String>(comparator);
		for (String txt : txts) {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(USERS + "/" + username + "/" + txt), "UTF-8"));
			String text = null;
			StringBuilder builder = new StringBuilder();
			while ((text = reader.readLine()) != null) {
				builder.append(text);
			}
			Date date = new Date(Long.parseLong(txt.substring(0, txt.indexOf(".txt"))));
			messages.put(date, builder.toString());
			reader.close();
		}

		return messages;
	}
	
	//������Ϣ
    public void addMessage(String username, String blabla) 
    		throws IOException {
        String file = USERS + "/" + username + "/" + new Date().getTime() + ".txt";
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        writer.write(blabla);
        writer.close();
    }
    
    //ɾ����Ϣ
    public void deleteMessage(String username, String message) {
    	File file = new File(USERS + "/" + username + "/" + message + ".txt");
        if(file.exists()) {
            file.delete();
        }
    }

}
