// Ppd3.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "pch.h"
#include <iostream>
#include <mutex>
#include <array>
using namespace std;
class Account {
	int accountNumber ;
	float amount;
	std::mutex mtx;
public:
	Account() {};
	/*Account(int accountNumber, float amount) {
		this->accountNumber = accountNumber;
		this->amount = amount;
	}*/
	Account(&account) ;
	Account(&account) {
		this->accountNumber = account.getAccount();
		this->amount = account.getAmount();
	}
	//Account(int accountNumber, float amount) : accountNumber(accountNumber), amount(amount) {};
	~Account() {
	
	};
	void add(float amount) {
		mtx.lock();
		this->amount = this->amount + amount;
		mtx.unlock();
	}
	void subtract(float amount) {
		mtx.lock();
		if (this->amount < amount) cout << "Not enough money in Account";
		else this->amount = this->amount - amount;
		mtx.unlock();
	}
	void setAccount(int account) {
		this->accountNumber = account;
	}
	void setAmount(float amount) {
		this->amount = amount;
	}
	//string toString() {
		//return std::to_string((int)this->accountNumber)+std::to_string((int) this->amount);
	//}
	int getAccount() {
		return this->accountNumber;
	}
	float getAmount() {
		return this->amount;
	}
};

int main()
{
    std::cout << "Hello World!\n";
	array<Account, 10> accounts; 
	for (int i = 0; i < 10; i++) {
		Account account ;
		account.setAccount(i);
		account.setAmount(i);
		accounts[i] = account;
		cout << accounts[i].getAccount(), accounts[i].getAmount();
	}

}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
