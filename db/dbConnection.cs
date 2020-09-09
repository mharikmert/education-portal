using System;
using MySql.Data;
using MySql.Data.MySqlClient;
namespace dbConnection{
    class Program{
        static void main(string[] args){
			connString ="" ;
			MySqlConnection connection;
			connString = "server = localhost; database = FikirtepeDB; username = root; password = ";
			connection = new MySqlConnection(connString);
			try{
				connection.Open();
				Console.WriteLine("connection successfully");
				connection.Close();
			}catch(Exception ex){
				Console.WriteLine("connection failed");
			}
        }
    }
}