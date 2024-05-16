/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: sel-kham <sel-kham@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2023/09/16 01:34:42 by sel-kham          #+#    #+#             */
/*   Updated: 2023/09/17 06:20:44 by sel-kham         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

class Main
{
	public static void main(String args[])
	{
		int		number;

		number = 479598;
		System.out.println(sumOfDigits(number));
	}

	public static int sumOfDigits(int number)
	{
		if (number == 0)
			return (0);
		else
			return (number % 10 + sumOfDigits(number / 10));
	}
}