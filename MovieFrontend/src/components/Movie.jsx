import React, {useEffect, useState} from 'react';
import {FaHeart, FaRegHeart} from 'react-icons/fa';
import {UserAuth} from '../Context/AuthContext';
import axios from 'axios';
import { Box, Modal, Typography } from '@mui/material';

const Movie = ({item}) => {
    const [like, setLike] = useState(false)
    const { user } = UserAuth()
    const [open, setOpen] = useState(false)
    
    useEffect(() => {
      const fetchData = async () => {
        const { data } = await axios.get(`${process.env.REACT_APP_JAVA_BACKEND_URL}/netflix/movies/liked/user/${user.id}`)
        if (data.find(({id}) => item.id === id)) {
          setLike(true);
        }
      }
      fetchData();
      }, [])


    const savedMovie = () => {
      if(user?.id){
        const request = like ? axios.delete : axios.post
        request(`${process.env.REACT_APP_JAVA_BACKEND_URL}/netflix/users/${user.id}/liked/movie/${item.id}`)
        setLike(!like)
      }
      else{
        alert('Please log in to save movie.')
      }
    };
 
  return (
    <>
    <Modal
        open={open}
        onClose={() => setOpen(false)}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
        style={{display:'flex',alignItems:'center',justifyContent:'center'}}
      >
        <Box style={{backgroundColor:'red'}}>
          <p className='white-space-normal text-xs md:text-sm font-bold flex justify-center items-center h-full text-center '>{item.title}</p>
          <iframe width="560" height="315" src="https://www.youtube.com/embed/aIsFywuZPoQ" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
          <p className='white-space-normal text-xs md:text-sm font-bold flex justify-center items-center h-full text-center '>Release Date: {new Date(item.releaseDate).toDateString()}</p> 
          <p className='white-space-normal text-xs md:text-sm font-bold flex justify-center items-center h-full text-center '>Category: {item.genre}</p> 
          <p className='white-space-normal text-xs md:text-sm font-bold flex justify-center items-center h-full text-center '>Movie Director: {item.movieDirector ?? "Djamal"}</p> 
          <p className='white-space-normal text-xs md:text-sm font-bold flex justify-center items-center h-full text-center '>Rating: {item.rating}</p> 
        </Box>
    </Modal>
    
    <div onClick={() => setOpen(true)} className='w-[160px] sm:w-[200px] md:w-[240px] lg:w-[280px] inline-block cursor-pointer relative p-2'>
      
      <img className ='w-full h-auto block' src={item?.imgUrl} alt={item?.title} />
      <div className='absolute top-0 left-0 w-full h-full hover:bg-black/80 opacity-0 hover:opacity-100 text-white'>
          <p className='white-space-normal text-xs md:text-sm font-bold flex justify-center items-center h-full text-center'>{item?.title}</p>
          <p onClick={savedMovie}>
              {like ? ( <FaHeart className='absolute top-4 left-4 text-gray-300'/> ) : ( <FaRegHeart className='absolute top-4 left-4 text-gray-300'/> )}
          </p>
      </div>
    </div>
    </>
  );
};

export default Movie;