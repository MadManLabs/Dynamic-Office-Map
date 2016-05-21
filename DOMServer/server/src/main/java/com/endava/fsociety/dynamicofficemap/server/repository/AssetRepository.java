package com.endava.fsociety.dynamicofficemap.server.repository;

import com.endava.fsociety.dynamicofficemap.server.model.Asset;
import com.endava.fsociety.dynamicofficemap.server.model.AssetType;
import com.endava.fsociety.dynamicofficemap.server.model.Floor;
import com.endava.fsociety.dynamicofficemap.server.model.Zone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

@Repository
public interface AssetRepository extends MongoRepository<Asset, String> {

    List<Asset> findByAssetType(AssetType assetType);

    Asset findByCode(String code);

    List<Asset> findByZone(Zone zone);

    List<Asset> findByZoneAndAssetType(Zone zone, AssetType assetType);

    List<Asset> findByZoneAndIsOnMap(Zone zone, boolean isOnMap);

}
