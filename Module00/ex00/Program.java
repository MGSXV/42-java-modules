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
		int		sum;

		number = 55;
		sum = 0;
		while (number > 0)
		{
			sum += number % 10;
			number /= 10;
		}
		System.out.println(sum);
	}
}