import axios from 'axios'
import React, { useEffect, useState } from 'react'
import requests from '../Requests'

const Main = ({movies}) => {

    const [movie, setMovie] = useState(movies[Math.floor(Math.random() * movies.length)])

    setInterval(() => {
        setMovie(movies[Math.floor(Math.random() * movies.length)])
    }, 5000)

    

    const troncateString = (str, num) => {
        if(str?.length > num){
            return str.slice(0, num) + '...';
        }else{
            return str;
        }
    };



    const releaseDate = movie?.releaseDate ? new Date(movie.releaseDate).toDateString() : "";

  return (
    <div className='w-full h-[550px] text-white'>
        <div className='w-full h-full'>
            <div className='absolute w-full h-[550px] bg-gradient-to-r from-black'></div>
            <img className='w-full h-full object-cover' src={movie?.imgUrl} alt={movie?.title} />
            <div className='absolute w-full top-[20%] p-4 md:p-8'>
                <h1 className='text-3xl md:text-5xl font-bold'>{movie?.title}</h1>
                <div className = 'my-4'>
                    <button className='border bg-gray-300 text-black border-gray-300 py-2 px-5'>
                        Play
                    </button>
                    <button className='border text-white border-gray-300 py-2 px-5 ml-4'>
                        Watch Later
                    </button>
                </div>
                <p className = 'text-gray-400 text-sm'>Released: {releaseDate}</p>
                <p className = 'text-gray-400 text-sm'>Rating: {movie?.rating}</p>
                <p className = 'w-full md:max-w-[70%] lg:max-w-[50%] xl:max-w-[35%] text-gray-200'>{troncateString(movie?.overview, 150)}</p>
            </div>
        </div>
    </div>
  )
}

export default Main