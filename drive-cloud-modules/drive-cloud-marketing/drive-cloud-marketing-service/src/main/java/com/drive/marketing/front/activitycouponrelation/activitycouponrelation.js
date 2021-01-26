import request from '@/utils/request'


// 查询活动优惠券关联表列表
export function listActivityCouponRelation(query) {
  return request({
    url: '/marketing/activityCouponRelation/pageList',
    method: 'get',
    params: query
  })
}

// 查询活动优惠券关联表详细
export function getActivityCouponRelation(id) {
  return request({
    url: '/marketing/activityCouponRelation/' + id,
    method: 'get'
  })
}

// 新增活动优惠券关联表
export function addActivityCouponRelation(data) {
  return request({
    url: '/marketing/activityCouponRelation',
    method: 'post',
    data: data
  })
}

// 修改活动优惠券关联表
export function updateActivityCouponRelation(data) {
  return request({
    url: '/marketing/activityCouponRelation',
    method: 'put',
    data: data
  })
}

// 删除活动优惠券关联表
export function delActivityCouponRelation(id) {
  return request({
    url: '/marketing/activityCouponRelation/' + id,
    method: 'delete'
  })
}

// 导出活动优惠券关联表
export function exportActivityCouponRelation(query) {
  return request({
    url: '/marketing/activityCouponRelation/exportXls',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}