/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: mgs <mgs@student.42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2023/09/16 01:34:42 by sel-kham          #+#    #+#             */
/*   Updated: 2024/07/06 14:31:00 by mgs              ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

class Program
{
	public static void main(String args[])
	{
		int		number;
		int		res;

		number = 479598;
		res = 0;
		res += number % 10;
		number /= 10;
		res += number % 10;
		number /= 10;
		res += number % 10;
		number /= 10;
		res += number % 10;
		number /= 10;
		res += number % 10;
		number /= 10;
		res += number % 10;
		System.out.println(res);
	}
}