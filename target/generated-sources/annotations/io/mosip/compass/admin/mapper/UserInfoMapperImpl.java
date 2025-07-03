package io.mosip.compass.admin.mapper;

import io.mosip.compass.admin.dto.UserInfoDTO;
import io.mosip.compass.admin.dto.UserInfoResponseDTO;
import io.mosip.compass.admin.entity.UserInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-27T06:21:28+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Ubuntu)"
)
@Component
public class UserInfoMapperImpl implements UserInfoMapper {

    @Override
    public UserInfo toEntity(UserInfoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserInfo userInfo = new UserInfo();

        userInfo.setFaceImageGrey( convertColorBase64ToGreyBase64( dto.getFaceImageColor() ) );
        userInfo.setBirthCountry( dto.getBirthCountry() );
        userInfo.setCardAccessNumber( dto.getCardAccessNumber() );
        userInfo.setDateOfBirth( dto.getDateOfBirth() );
        userInfo.setEmail( dto.getEmail() );
        userInfo.setFaceImageColor( dto.getFaceImageColor() );
        userInfo.setFirstNamePrimary( dto.getFirstNamePrimary() );
        userInfo.setGender( dto.getGender() );
        userInfo.setLastNameSecondary( dto.getLastNameSecondary() );
        userInfo.setNationalUid( dto.getNationalUid() );
        userInfo.setNationality( dto.getNationality() );
        userInfo.setFirstNamePrimaryLatin( dto.getFirstNamePrimaryLatin() );
        userInfo.setLastNameSecondaryLatin( dto.getLastNameSecondaryLatin() );

        userInfo.setCreatedTimes( java.time.LocalDateTime.now() );

        return userInfo;
    }

    @Override
    public UserInfoDTO toDto(UserInfo entity) {
        if ( entity == null ) {
            return null;
        }

        UserInfoDTO userInfoDTO = new UserInfoDTO();

        userInfoDTO.setIssuanceDate( mapCreatedTimesToIssuedDate( entity.getCreatedTimes() ) );
        userInfoDTO.setUserInfoId( entity.getUserInfoId() );
        userInfoDTO.setBirthCountry( entity.getBirthCountry() );
        userInfoDTO.setCardAccessNumber( entity.getCardAccessNumber() );
        userInfoDTO.setDateOfBirth( entity.getDateOfBirth() );
        userInfoDTO.setEmail( entity.getEmail() );
        userInfoDTO.setFaceImageColor( entity.getFaceImageColor() );
        userInfoDTO.setFirstNamePrimary( entity.getFirstNamePrimary() );
        userInfoDTO.setGender( entity.getGender() );
        userInfoDTO.setLastNameSecondary( entity.getLastNameSecondary() );
        userInfoDTO.setFirstNamePrimaryLatin( entity.getFirstNamePrimaryLatin() );
        userInfoDTO.setLastNameSecondaryLatin( entity.getLastNameSecondaryLatin() );
        userInfoDTO.setNationalUid( entity.getNationalUid() );
        userInfoDTO.setNationality( entity.getNationality() );
        userInfoDTO.setCompassId( entity.getCompassId() );

        return userInfoDTO;
    }

    @Override
    public void updateEntityFromDto(UserInfoDTO userInfoDTO, UserInfo userInfo) {
        if ( userInfoDTO == null ) {
            return;
        }

        userInfo.setFaceImageGrey( convertColorBase64ToGreyBase64( userInfoDTO.getFaceImageColor() ) );
        userInfo.setBirthCountry( userInfoDTO.getBirthCountry() );
        userInfo.setCardAccessNumber( userInfoDTO.getCardAccessNumber() );
        userInfo.setDateOfBirth( userInfoDTO.getDateOfBirth() );
        userInfo.setEmail( userInfoDTO.getEmail() );
        userInfo.setFaceImageColor( userInfoDTO.getFaceImageColor() );
        userInfo.setFirstNamePrimary( userInfoDTO.getFirstNamePrimary() );
        userInfo.setGender( userInfoDTO.getGender() );
        userInfo.setLastNameSecondary( userInfoDTO.getLastNameSecondary() );
        userInfo.setNationalUid( userInfoDTO.getNationalUid() );
        userInfo.setNationality( userInfoDTO.getNationality() );
        userInfo.setFirstNamePrimaryLatin( userInfoDTO.getFirstNamePrimaryLatin() );
        userInfo.setLastNameSecondaryLatin( userInfoDTO.getLastNameSecondaryLatin() );

        userInfo.setUpdatedTimes( java.time.LocalDateTime.now() );
    }

    @Override
    public UserInfoResponseDTO toResponseDto(UserInfo entity) {
        if ( entity == null ) {
            return null;
        }

        UserInfoResponseDTO userInfoResponseDTO = new UserInfoResponseDTO();

        userInfoResponseDTO.setUserInfoId( entity.getUserInfoId() );
        userInfoResponseDTO.setNationalUid( entity.getNationalUid() );

        return userInfoResponseDTO;
    }

    @Override
    public List<UserInfoDTO> toDtoList(List<UserInfo> entities) {
        if ( entities == null ) {
            return null;
        }

        List<UserInfoDTO> list = new ArrayList<UserInfoDTO>( entities.size() );
        for ( UserInfo userInfo : entities ) {
            list.add( toDto( userInfo ) );
        }

        return list;
    }

    @Override
    public List<UserInfo> toEntityList(List<UserInfoDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<UserInfo> list = new ArrayList<UserInfo>( dtos.size() );
        for ( UserInfoDTO userInfoDTO : dtos ) {
            list.add( toEntity( userInfoDTO ) );
        }

        return list;
    }
}
