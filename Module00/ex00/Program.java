/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: mgs <mgs@student.42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2023/09/16 01:34:42 by sel-kham          #+#    #+#             */
/*   Updated: 2024/07/06 14:15:04 by mgs              ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

class Program
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
		return (number % 10 + sumOfDigits(number / 10));
	}
}